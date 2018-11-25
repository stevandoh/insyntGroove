


import com.johnny.behwe.models.UserProfileMDL
import retrofit2.Call
import retrofit2.http.*

interface BaseApiService {
    @FormUrlEncoded
    @POST("auth/login/agent")
    fun signinRequest(
        @Field("email") username: String,
        @Field("password") password: String
//        @Field("deviceToken") playerId: String
    ): Call<UserProfileMDL>

    @FormUrlEncoded
    @POST("/auth/password/forgot")
    fun forgotPasswordRequest(
//        @Path("id") id: String,
        @Field("email") username: String
//        @Field("deviceToken") playerId: String
    ): Call<ServerResponse>

    @FormUrlEncoded
    @POST("auth/password/reset")
    fun resetPasswordRequest(
        @Field("userId") userId: String,
        @Field("token") token: String,
        @Field("newPassword") newPassword: String,
        @Field("confirmPassword") confirmPassword: String
    ): Call<ServerResponse>

    @FormUrlEncoded
    @POST("/auth/signup")
    fun  SignupRequest(
        @Field("firstname") firstname: String,
        @Field("lastname") lastname: String,
        @Field("phone") phone: String,
        @Field("email") username: String,
        @Field("password") password: String
//        @Field("deviceToken") playerId: String
    ): Call<ServerResponse>
}
