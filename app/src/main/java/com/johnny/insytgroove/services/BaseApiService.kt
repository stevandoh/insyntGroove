package com.johnny.insytgroove.services

import com.johnny.insytgroove.models.PostMDL
import com.johnny.insytgroove.models.UserMDL
import com.johnny.insytgroove.models.UserProfileMDL
import com.johnny.insytgroove.pojo.ServerResponse
import com.johnny.insytgroove.pojo.UserList
import retrofit2.Call
import retrofit2.http.*
import com.johnny.insytgroove.pojo.User
import io.realm.RealmList
import io.realm.RealmSchema
import retrofit2.Callback
import retrofit2.http.GET



interface BaseApiService {



    @GET("posts/{id}")
    fun getPostWithID(@Path("id") id: Int): Call<PostMDL>

    @GET("posts")
    fun getPostOfUser(@Query("userId") id: Int): Call<List<PostMDL>>

    @POST("posts")
    fun postData(@Body data: PostMDL): Call<PostMDL>

    @GET("users")
    fun getAllUserRequest(): Call<RealmList<UserMDL>>

    @GET("posts")
    fun getAllPostsRequest(): Call<RealmList<PostMDL>>



}
