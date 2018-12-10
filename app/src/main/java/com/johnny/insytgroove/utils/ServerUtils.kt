package com.johnny.insytgroove.utils

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import com.google.gson.GsonBuilder
import com.johnny.insytgroove.AppController
import com.johnny.insytgroove.R
import com.johnny.insytgroove.models.UserProfileMDL
import com.johnny.insytgroove.pojo.ServerResponse
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.*
import java.io.File.separator
import java.net.ConnectException
import java.util.concurrent.TimeoutException


class ServerUtils {
    companion object {
        @JvmStatic
        fun checkConnectivity(t: Throwable, context: Context) {
            if (t is ConnectException) {
                t.printStackTrace()
                MaterialDialog(context)
                    .title(text = "Network Error")
                    .message(text = "Slow or No Internet Connection.\n Please check your connectivity")
                    .show()
            } else if (t is TimeoutException) {
                MaterialDialog(context)
                    .title(text = "Network Error")
                    .message(text = "Slow or No Internet Connection.\n Please check your connectivity")
                    .positiveButton(R.string.btn_ok)

                    .show()
            }
        }

        @JvmStatic
        public fun getErrorMsg(context: Context, response: Response<ServerResponse>) {
            val gson = GsonBuilder().create()
            val jsonObject = gson.fromJson(
                response.errorBody()!!.string()
                , ServerResponse::class.java
            )

            GenUtils.getToastMessage(
                context
                , jsonObject.message
            )
        }

        @JvmStatic
        public fun getErrorMsgfromResponseBody(context: Context, response: Response<ResponseBody>) {
            val gson = GsonBuilder().create()
            val jsonObject = gson.fromJson(
                response.errorBody()!!.string()
                , ServerResponse::class.java
            )

            GenUtils.getToastMessage(
                context
                , jsonObject.message
            )
        }

        @JvmStatic
        public fun getErrorSigninMsg(context: Context, response: Response<UserProfileMDL>) {
            val gson = GsonBuilder().create()
            val jsonObject = gson.fromJson(
                response.errorBody()!!.string()
                , ServerResponse::class.java
            )

            GenUtils.getToastMessage(
                context
                , jsonObject.message
            )
        }




        @JvmStatic
        fun setPictureUrl(pictureUrl: String): String {
            var newUrl = ""
            if (pictureUrl.isNotEmpty()) {
                newUrl = AppController.BASE_API.substringBefore("v1/")
                    .plus(pictureUrl.substring(1))
            }
            return newUrl

        }

        @JvmStatic
        fun setPicServerPath(pictureUrl: String): String {
            var newUrl = ""
            if (pictureUrl.isNotEmpty()) {
                newUrl = pictureUrl.substring(1)
            }
            return newUrl

        }

//        https://futurestud.io/tutorials/retrofit-2-how-to-download-files-from-server

        @JvmStatic
        fun writeResponseBodyToDisk(context: Context, body: ResponseBody, filename: String)
                : Boolean {
            try {
                val imageGame =
                    File(
                        context.getExternalFilesDir(null).absolutePath
                                + separator
                                + filename
                    )
                var inputStream: InputStream? = null
                var outputStream: OutputStream? = null

                try {
                    val fileReader = ByteArray(4096)
                    val fileSize = body.contentLength()
                    var fileSizeDownloaded: Long = 0

                    inputStream = body.byteStream()
                    outputStream = FileOutputStream(imageGame)

                    while (true) {
                        val read = inputStream!!.read(fileReader)

                        if (read == -1) {
                            break
                        }

                        outputStream.write(fileReader, 0, read)

                        fileSizeDownloaded += read.toLong()

//                        Log.d(TAG, "file download: $fileSizeDownloaded of $fileSize")
                    }

                    outputStream.flush()


                    return true
                } catch (e: IOException) {
                    return false
                } finally {
                    if (inputStream != null) {
                        inputStream.close()
                    }

                    if (outputStream != null) {
                        outputStream.close()
                    }
                }
            } catch (e: IOException) {
                return false
            }


        }
    }
}