package com.test.miprimeraapp.data.remote

import retrofit2.http.*

interface JsonPlaceholderService{

    @GET("posts")
    suspend fun getPosts():List<PostResponse>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id:Long):PostResponse

    @POST("posts")
    suspend fun createPost(@Body body:PostResquest):PostResponse

    @PATCH("posts")
    suspend fun updatePost(@Path("id") id:Long,@Body body:PostResquest):PostResponse

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id:Long)
}