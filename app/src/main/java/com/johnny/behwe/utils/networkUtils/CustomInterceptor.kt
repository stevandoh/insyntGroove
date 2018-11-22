

import android.util.Log
import com.johnny.behwe.models.UserProfileMDL
import com.vicpin.krealmextensions.queryFirst
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class CustomInterceptor : Interceptor {
    //
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val originalRequest = chain.request()
        val token = getToken()
        Log.d("token", token)
        if ((POST == originalRequest.method() && token.isNotEmpty())
                ||
                (PUT == originalRequest.method() && token.isNotEmpty())
                ||
                (GET == originalRequest.method() && token.isNotEmpty())) {
            val secureRequest = originalRequest
                    .newBuilder()
//                .addHeader("x-tenant", "unibank")
                    .addHeader("x-access-token", token)
                    .build()
            return chain.proceed(secureRequest)

        }
        return chain.proceed(originalRequest)
    }

    companion object {
        val POST = "POST"
        val PUT = "PUT"
        val GET = "GET"

//        val token = getToken()
    }

    private fun getToken(): String {
        val userProfileMDL = UserProfileMDL().queryFirst()
        var token = ""
        if (userProfileMDL != null) {
            token = userProfileMDL.token!!
        }
        return token
    }
}
