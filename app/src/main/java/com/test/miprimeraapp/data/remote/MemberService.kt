package com.test.miprimeraapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MemberService{

    @GET(".")
    suspend fun getMember():UiName

    @GET(".")
    suspend fun getMembers(@Query("amount") amount:Int):List<UiName>
}