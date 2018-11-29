package com.johnny.behwe.activities

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.EventLogTags
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.assent.Permission
import com.afollestad.assent.runWithPermissions
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.files.fileChooser
import com.johnny.behwe.R
import com.johnny.behwe.utils.GenUtils

import kotlinx.android.synthetic.main.activity_create_ad.*
import android.view.MotionEvent
import androidx.annotation.RequiresApi
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.cloudinary.android.MediaManager
import com.johnny.behwe.models.AdMDL
import com.johnny.behwe.pojo.ServerResponse
import com.johnny.behwe.services.BaseApiService
import com.johnny.behwe.services.UtilsApi
import com.johnny.behwe.utils.FormattingUtils
import com.johnny.behwe.utils.ServerUtils
import com.vicpin.krealmextensions.save
import kotlinx.android.synthetic.main.activity_user_management.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class CreateAdActivity :  AppCompatActivity() {
    private var mApiService: BaseApiService? = null
    private var filePath= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ad)
        setContentView(R.layout.activity_user_management)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        mApiService = UtilsApi.apiService


        imgUpload.setOnClickListener{
            showFileChooser()
        }


        btnCreateAd.setOnClickListener{
            if (!filePath.isEmpty()){
                uploadImgToCloudinary(filePath)
            } else{
                GenUtils.getToastMessage(applicationContext,"Try again")
            }
        }




    }

    private fun showFileChooser() = runWithPermissions(Permission.READ_EXTERNAL_STORAGE) {
        MaterialDialog(this).show {
            fileChooser { _, file ->
//             val upload: String   = uploadImg(file.path)
                filePath  = file.path
//                tvImgPath. = filePath
            }

        }

    }

    private fun uploadImgToCloudinary(imgPath: String): String{
        var  imgUrl = ""
         MediaManager.get()
            .upload(imgPath)
            .unsigned("gohoppjb")
            .callback(object : UploadCallback {
            override fun onStart(requestId: String) {
                // your code here
                progressBar.visibility =View.VISIBLE
            }

            override fun onProgress(requestId: String, bytes: Long, totalBytes: Long) {
                // example code starts here
                val progress = bytes.toDouble() / totalBytes

                progressBar.visibility =View.VISIBLE
                // post progress to app UI (e.g. progress bar, notification)
                // example code ends here
            }

            override fun onSuccess(requestId: String, resultData: Map<*, *>) {
                // your code here
                progressBar.visibility =View.GONE
                GenUtils.getToastMessage(applicationContext,"awesome")

                imgUrl = resultData["url"].toString()
//                tvImgPath.text = imgUrl
                uploadImg(imgUrl)
                Log.d("trial",imgUrl)

            }

            override fun onError(requestId: String, error: ErrorInfo) {
                // your code here
                progressBar.visibility =View.GONE
                GenUtils.getToastMessage(applicationContext,"Upload failed")
            }

            override fun onReschedule(requestId: String, error: ErrorInfo) {
                // your code here
            }
        })
            .dispatch()

        return imgUrl

    }

    private fun uploadImg(cloudinaryUrl:String) {
        val titleError = GenUtils.isEmpty(
            tiTitle.editText
            , tiTitle
            , "Title is required"
        )
        val descriptionError = GenUtils.isEmpty(
            tiDescription.editText
            , tiDescription
            , "Description is required"
        )




        if (!( descriptionError && titleError  )) {
            tiTitle.error = "Title field cannot be empty"
            tiDescription.error = "Description field cannot be empty"

            GenUtils.getToastMessage(applicationContext, "None of the fields must be empty")
        }
        if(cloudinaryUrl.isEmpty()){
            GenUtils.getToastMessage(applicationContext, "Select your media")
        }

        else {
//            if (!GenUtils.isEmailisValid(tiEmail.editText, tiEmail, "Please enter a valid Email")) {
//                mProgressDialog!!.setMessage("Please wait...")
//                mProgressDialog!!.show()

            progressBar.visibility = View.VISIBLE

            mApiService!!.createAdRequest(
               tiTitle.editText!!.text.toString(),
                tiDescription.editText!!.text.toString(),
                cloudinaryUrl
            ).enqueue(object : Callback<ServerResponse> {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                override fun onResponse(
                    call: Call<ServerResponse>
                    , response: Response<ServerResponse>
                ) {
                    when {
                        response.isSuccessful -> {
                            progressBar.visibility = View.GONE
                            Log.d("Result", response.body().toString())
                            GenUtils.getToastMessage(this@CreateAdActivity,response.message())
                            val id = UUID.randomUUID().toString()
                            AdMDL(title = tiTitle.editText!!.text.toString(),
                                cloudinaryPath = cloudinaryUrl,
                                description = tiDescription.editText!!.text.toString(),
                                mediaPath =filePath,
                                id = id).save()
                            startActivity(Intent(this@CreateAdActivity,MainActivity::class.java))

                        }
                        response.code() == 403 -> {
                            progressBar.visibility = View.GONE
                            ServerUtils.getErrorMsg(applicationContext, response)
                        }
                        response.code() == 422 -> {

                            progressBar.visibility = View.GONE
                            ServerUtils.getErrorMsg(applicationContext, response)


                        }

                    }
                }

                override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
//                        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
//                            mProgressDialog!!.dismiss()
//                        }
                    progressBar.visibility = View.VISIBLE
                    Log.e("debug", "onFailure: ERROR > " + t.message)
                    ServerUtils.checkConnectivity(t, this@CreateAdActivity)

                }
            })

//            }
        }
    }




}
