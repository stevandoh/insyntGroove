package com.johnny.insytgroove.services

import com.johnny.insytgroove.models.PostMDL
import retrofit2.Call
import retrofit2.http.*

interface JsonplaceholderInterface {

    @get:GET("/posts")
    val allPosts: Call<List<PostMDL>>

    @GET("/posts/{id}")
    fun getPostWithID(@Path("id") id: Int): Call<PostMDL>

    @GET("/posts")
    fun getPostOfUser(@Query("userId") id: Int): Call<List<PostMDL>>

    @POST("/posts")
    fun postData(@Body data: PostMDL): Call<PostMDL>


}
