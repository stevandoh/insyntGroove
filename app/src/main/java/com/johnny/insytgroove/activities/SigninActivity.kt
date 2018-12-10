package com.johnny.behwe.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.WhichButton
import com.afollestad.materialdialogs.actions.setActionButtonEnabled
import com.afollestad.materialdialogs.input.getInputField
import com.afollestad.materialdialogs.input.input
import com.google.gson.GsonBuilder
import com.johnny.behwe.R
import com.johnny.behwe.models.UserProfileMDL
import com.johnny.behwe.pojo.ServerResponse
import com.johnny.behwe.services.BaseApiService
import com.johnny.behwe.services.UtilsApi
import com.johnny.behwe.utils.FormattingUtils
import com.johnny.behwe.utils.FormattingUtils.Companion.validateEmail
import com.johnny.behwe.utils.GenUtils
import com.johnny.behwe.utils.ServerUtils
import com.johnny.behwe.utils.ServerUtils.Companion.checkConnectivity
import com.johnny.behwe.utils.SharedPrefManager
import com.vicpin.krealmextensions.save
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_signin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SigninActivity : AppCompatActivity() {

    private var mApiService: BaseApiService? = null
    //    private var mImgService: ImageService? = null
    private var mProgressDialog: ProgressDialog? = null
    internal var mSharedPrefManager: SharedPrefManager? = null
    private var mRealm: Realm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        mApiService = UtilsApi.apiService
//        mImgService = UtilsImageDownload.imageService
        mSharedPrefManager = SharedPrefManager(applicationContext)

        checkAlreadyLogin()

        btnSignin.setOnClickListener {
            login(it)

        }
        btnSignup.setOnClickListener {
            startActivity(Intent(this@SigninActivity, SignupActivity::class.java))
        }
        tvForgotPwd.setOnClickListener {
            displayForgotPwdDialog()
        }
    }

    private fun displayForgotPwdDialog() {
        val type = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        MaterialDialog(this@SigninActivity)
            .title(text = "Forgot Password")
            //                    .content("Please enter your email")

//            .input()
//            .inputRangeRes(1, 50, R.color.md_red_500)
//            .inputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
            .input("Please enter your email", null, null, null, type, 50) { dialog, text ->
                val inputField = dialog.getInputField()
                val isValid = text.startsWith("a", true)

                inputField?.error = if (validateEmail(inputField.toString())) null else "Please enter a valid email"
                dialog.setActionButtonEnabled(WhichButton.POSITIVE, isValid)

                setForgotPwd(inputField.toString())
            }
            .negativeButton(R.string.btn_cancel)
            .positiveButton(R.string.btn_ok) {


            }

            .show {
                //                input(inputType = type)
            }
    }

    private fun setUpLogin() {
        mSharedPrefManager!!.saveIsLoggedIn(this, true)
//        Toast.makeText(this@SigninActivity, "Login successful",
//                Toast.LENGTH_SHORT).show()
        val intent = Intent(this@SigninActivity, DashboardActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun checkAlreadyLogin() {
        if (mSharedPrefManager!!.isLogged_IN) { //IS_LOGGED_IN
            val intent = Intent(this@SigninActivity, DashboardActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()

        }
    }

    private fun login(view: View) {
        val passwordError = GenUtils.isEmpty(
            tiPwd.editText
            , tiPwd
            , "Password is required"
        )
        val usernameError = GenUtils.isEmpty(
            tiEmail.editText
            , tiEmail
            , "Email is required"
        )
        val emailError = FormattingUtils.validateEmail(tiEmail.editText.toString())


        if (!(passwordError && usernameError)) {
            tiEmail.error = "Email field cannot be empty"
            tiPwd.error = "Password field cannot be empty"
            GenUtils.getToastMessage(applicationContext, "None of the fields must be empty")
        } else if(!isPasswordValid(tiPwd.editText!!.text)){
            tiPwd.error ="Password must contain at least 8 characters"
        }
        else if (!emailError) {
            tiEmail.error = "Enter a valid email"
            GenUtils.getToastMessage(applicationContext, "Enter a valid email")
        } else {
//            if (!GenUtils.isEmailisValid(tiEmail.editText, tiEmail, "Please enter a valid Email")) {
//                mProgressDialog!!.setMessage("Please wait...")
//                mProgressDialog!!.show()
            progressBar.visibility = View.VISIBLE

            mApiService!!.signinRequest(
                tiEmail.editText!!.text.toString().trim()
                , tiPwd.editText!!.text.toString()
            ).enqueue(object : Callback<UserProfileMDL> {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                override fun onResponse(
                    call: Call<UserProfileMDL>
                    , response: Response<UserProfileMDL>
                ) {
                    when {
                        response.isSuccessful -> {
                            progressBar.visibility = View.GONE
                            Log.d("Result", response.body().toString())
                            Toast.makeText(
                                this@SigninActivity
                                , "login successful", Toast.LENGTH_LONG
                            ).show()
                            response.body()!!.save()
                            setUpLogin()
                        }
                        response.code() == 403 -> {
                            progressBar.visibility = View.GONE
                            ServerUtils.getErrorSigninMsg(applicationContext, response)
                        }
                        response.code() == 422 -> {
                            progressBar.visibility = View.GONE
//                            val gson = GsonBuilder().create()
                            ServerUtils.getErrorSigninMsg(applicationContext, response)
                        }

                    }
                }

                override fun onFailure(call: Call<UserProfileMDL>, t: Throwable) {
//                        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
//                            mProgressDialog!!.dismiss()
//                        }
                    progressBar.visibility = View.GONE
                    Log.e("debug", "onFailure: ERROR > " + t.message)
                    checkConnectivity(t, this@SigninActivity)

                }
            })

//            }
        }
    }

    private fun setForgotPwd(username: String) {

        mApiService!!.forgotPasswordRequest(
            username

        ).enqueue(object : Callback<ServerResponse> {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            override fun onResponse(call: Call<ServerResponse>, response: Response<ServerResponse>) {
                if (response.isSuccessful) {
//                    dismissProgressDialog()
                    Log.d("Result", response.body().toString())
                    if (!response.body()!!.success) {
                        GenUtils.getToastMessage(applicationContext, response.body()!!.message)
                    }

                } else if (response.code() == 403) {
//                    dismissProgressDialog()
                    progressBar.visibility = View.GONE
                    ServerUtils.getErrorMsg(applicationContext, response)

                }
            }

            override fun onFailure(call: Call<ServerResponse>, t: Throwable) {

//                dismissProgressDialog()
                progressBar.visibility = View.GONE
                Log.e("debug", "onFailure: ERROR > " + t.message)
                checkConnectivity(t, this@SigninActivity)
            }
        })

    }


//    private fun dismissProgressDialog() {
//        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
//            mProgressDialog!!.dismiss()
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()

    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }


}
