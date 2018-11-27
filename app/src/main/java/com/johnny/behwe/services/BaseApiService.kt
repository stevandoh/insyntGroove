package com.johnny.behwe.services

import com.johnny.behwe.models.UserProfileMDL
import com.johnny.behwe.pojo.ServerResponse
import retrofit2.Call
import retrofit2.http.*

interface BaseApiService {
    @FormUrlEncoded
    @POST("auth/login")
    fun signinRequest(
        @Field("email") username: String,
        @Field("password") password: String
//        @Field("deviceToken") playerId: String
    ): Call<UserProfileMDL>

    @FormUrlEncoded
    @POST("auth/password/forgot")
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
    @POST("auth/signup")
    fun signupRequest(
        @Field("firstname") firstname: String,
        @Field("lastname") lastname: String,
//        @Field("phone") phone: String,
        @Field("email") username: String,
        @Field("password") password: String
//        @Field("deviceToken") playerId: String
    ): Call<ServerResponse>

    @GET("users/{id}")
    fun getUserDetailsRequest(
        @Path("id") id: String
    ): Call<ServerResponse>

    @FormUrlEncoded
    @POST("ads/create")
    fun createAdRequest(
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("media") media: String
    ): Call<ServerResponse>

    @FormUrlEncoded
    @PUT("ads/{id}")
    fun updateAdRequest(
        @Path("id") id: String,
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("media") media: String
    ): Call<ServerResponse>

    @GET("ads/{id}")
    fun getAdDetailsRequest(
        @Path("id") id: String
    ): Call<ServerResponse>

    @GET("ads?page=1&limit=50")
    fun getAdListRequest(
        @Path("id") id: String
    ): Call<ServerResponse>

    @DELETE("ads/{id}")
    fun deleteAdRequest(
        @Path("id") id: String
    ): Call<ServerResponse>

    @FormUrlEncoded
    @POST("payments")
    fun collectPaymentRequest(@Field("phone") phone: String,
                              @Field("amount") amount: String,
                              @Field("network") network: String

    ): Call<ServerResponse>

    @FormUrlEncoded
    @POST("payments")
    fun collectVodafonePaymentRequest(@Field("phone") phone: String,
                                      @Field("amount") amount: String,
                                      @Field("network") network: String,
                                      @Field("token") type: String

    ): Call<ServerResponse>
}
