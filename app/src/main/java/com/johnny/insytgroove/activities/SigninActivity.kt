package com.johnny.insytgroove.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.johnny.insytgroove.R
import com.johnny.insytgroove.models.PostMDL
import com.johnny.insytgroove.models.UserMDL
import com.johnny.insytgroove.services.BaseApiService
import com.johnny.insytgroove.services.UtilsApi
import com.johnny.insytgroove.utils.GenUtils
import com.johnny.insytgroove.utils.ServerUtils.Companion.checkConnectivity
import com.johnny.insytgroove.utils.SharedPrefManager
import com.vicpin.krealmextensions.saveAll
import io.realm.Realm
import io.realm.RealmList
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
        mRealm = Realm.getDefaultInstance()

        checkAlreadyLogin()

        btnSignin.setOnClickListener {
            login()

        }
    }


    private fun setUpLogin() {
        mSharedPrefManager!!.saveIsLoggedIn(this, true)
        Toast.makeText(
            this@SigninActivity, "Login successful",
            Toast.LENGTH_SHORT
        ).show()
        val intent = Intent(this@SigninActivity, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun checkAlreadyLogin() {
        if (mSharedPrefManager!!.isLogged_IN) { //IS_LOGGED_IN
            val intent = Intent(this@SigninActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

    private fun login() {
        val passwordError = GenUtils.isEmpty(
            tiPwd.editText
            , tiPwd
            , "Password is required"
        )
        val usernameError = GenUtils.isEmpty(
            tiUsername.editText
            , tiUsername
            , "Username is required"
        )
//        val emailError = FormattingUtils.validateUsername(tiUsername.editText.toString())


        if (!(passwordError && usernameError)) {
            tiUsername.error = "Username field cannot be empty"
            tiPwd.error = "Password field cannot be empty"
            GenUtils.getToastMessage(applicationContext, "None of the fields must be empty")
        } else if (!isPasswordValid(tiPwd.editText!!.text)) {
            tiPwd.error = "Password must contain at least 5 characters"
        } else {
//            if (!GenUtils.isUsernameisValid(tiUsername.editText, tiUsername, "Please enter a valid Username")) {
//                mProgressDialog!!.setMessage("Please wait...")
//                mProgressDialog!!.show()

            if (tiUsername.editText!!.text.toString() == "esoko" && tiPwd.editText!!.text.toString() == "insyt") {
                progressBar.visibility = View.VISIBLE
                mApiService!!.getAllUserRequest().enqueue(object : Callback<RealmList<UserMDL>> {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    override fun onResponse(
                        call: Call<RealmList<UserMDL>>
                        , response: Response<RealmList<UserMDL>>
                    ) {
                        when {
                            response.isSuccessful -> {
                                progressBar.visibility = View.GONE
                                Log.d("Result", response.body().toString())
                                Toast.makeText(
                                    this@SigninActivity
                                    , "login successful", Toast.LENGTH_LONG
                                ).show()

                                response.body()!!.saveAll()
                                getPosts()
                                setUpLogin()
                            }

                        }
                    }

                    override fun onFailure(call: Call<RealmList<UserMDL>>, t: Throwable) {

                        progressBar.visibility = View.GONE
                        Log.e("debug", "onFailure: ERROR > " + t.message)
                        checkConnectivity(t, this@SigninActivity)

                    }
                })

            }else{
                GenUtils.getToastMessage(applicationContext,"invalid username or password")
            }

        }
    }

    private fun getPosts(){

        mApiService!!.getAllPostsRequest().enqueue(object : Callback<RealmList<PostMDL>> {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            override fun onResponse(
                call: Call<RealmList<PostMDL>>
                , response: Response<RealmList<PostMDL>>
            ) {
                when {
                    response.isSuccessful -> {
                        progressBar.visibility = View.GONE
                        Log.d("Result", response.body().toString())
                        Toast.makeText(
                            this@SigninActivity
                            , "login successful", Toast.LENGTH_LONG
                        ).show()
                        response.body()!!.saveAll()
                    }


                }
            }

            override fun onFailure(call: Call<RealmList<PostMDL>>, t: Throwable) {
//                        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
//                            mProgressDialog!!.dismiss()
//                        }
                progressBar.visibility = View.GONE
                Log.e("debug", "onFailure: ERROR > " + t.message)
                checkConnectivity(t, this@SigninActivity)

            }
        })

    }
    override fun onDestroy() {
        super.onDestroy()

    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 5
    }


}
