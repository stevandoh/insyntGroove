package com.johnny.behwe.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.annotation.RequiresApi
import com.johnny.behwe.R
import com.johnny.behwe.pojo.ServerResponse
import com.johnny.behwe.services.BaseApiService
import com.johnny.behwe.services.UtilsApi
import com.johnny.behwe.utils.FormattingUtils
import com.johnny.behwe.utils.GenUtils
import com.johnny.behwe.utils.ServerUtils
import com.johnny.behwe.utils.ServerUtils.Companion.getErrorMsg
import com.johnny.behwe.utils.SharedPrefManager
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private var mApiService: BaseApiService? = null
    //    private var mImgService: ImageService? = null
    private var mProgressDialog: ProgressDialog? = null
    internal var mSharedPrefManager: SharedPrefManager? = null
    private var mRealm: Realm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mApiService = UtilsApi.apiService
//        mImgService = UtilsImageDownload.imageService
        mSharedPrefManager = SharedPrefManager(applicationContext)
        mProgressDialog = ProgressDialog(this)

        btnSignup.setOnClickListener{
            setSignup()

        }
    }

    private fun setSignup() {
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
        val firstnameError = GenUtils.isEmpty(
            tiFname.editText
            , tiFname
            , "Firstname is required"
        )
        val lastnameError = GenUtils.isEmpty(
            tiLname.editText
            , tiLname
            , "Lastname is required"
        )
//        val phoneError = GenUtils.isEmpty(
//            tiPhone.editText
//            , tiPhone
//            , "Phone number is required"
//        )
//        val phoneDigitError= GenUtils.isNotEnoughDigit(
//            tiPhone.editText
//            , tiPhone
//            , "Phone number is not valid"
//        )
        val emailError = FormattingUtils.validateEmail(tiEmail.editText.toString())


        if (!(firstnameError && lastnameError &&  usernameError && passwordError  )) {
            tiFname.error = "Firstname field cannot be empty"
            tiLname.error = "Lastname field cannot be empty"
            tiEmail.error = "Email field cannot be empty"
            tiPwd.error = "Password field cannot be empty"
            GenUtils.getToastMessage(applicationContext, "None of the fields must be empty")
        }else if(!isPasswordValid(tiPwd.editText!!.text)){
            tiPwd.error = "Password must contain at least 8 characters"

        }
//        else if(!phoneDigitError){
//            GenUtils.getToastMessage(applicationContext, "Enter a valid phone number")
//        }
        else if(!emailError){
            GenUtils.getToastMessage(applicationContext, "Enter a valid email")
        }
        else {
//            if (!GenUtils.isEmailisValid(tiEmail.editText, tiEmail, "Please enter a valid Email")) {
//                mProgressDialog!!.setMessage("Please wait...")
//                mProgressDialog!!.show()
            mProgressDialog!!.setMessage("Please wait...")
            mProgressDialog!!.show()
//            progressBar.visibility = View.VISIBLE

            mApiService!!.signupRequest(
                tiFname.editText!!.text.toString().trim(),
                tiLname.editText!!.text.toString().trim(),
//                tiPhone.editText!!.text.toString().trim(),
                tiEmail.editText!!.text.toString().trim()
                ,tiPwd.editText!!.text.toString()
            ).enqueue(object : Callback<ServerResponse> {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                override fun onResponse(
                    call: Call<ServerResponse>
                    , response: Response<ServerResponse>
                ) {
                    when {
                        response.isSuccessful -> {
                            dismissProgressDialog()
                            Log.d("Result", response.body().toString())
                            GenUtils.getToastMessage(this@SignupActivity,"Registration Successfull")

                            setUpLogin()
                        }
                        response.code() == 403 -> {
                            dismissProgressDialog()
                            getErrorMsg(applicationContext, response)



                        }
                        response.code() == 422 -> {

                            dismissProgressDialog()
                            getErrorMsg(applicationContext, response)


                        }

                    }
                }

                override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
//                        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
//                            mProgressDialog!!.dismiss()
//                        }
                    dismissProgressDialog()
                    Log.e("debug", "onFailure: ERROR > " + t.message)
                    ServerUtils.checkConnectivity(t, this@SignupActivity)

                }
            })

//            }
        }
    }



    private fun dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

    private fun setUpLogin() {
//        mSharedPrefManager!!.saveIsLoggedIn(this, true)
//        Toast.makeText(this@SigninActivity, "Login successful",
//                Toast.LENGTH_SHORT).show()
        val intent = Intent(this@SignupActivity, SigninActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }



}
