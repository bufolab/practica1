package com.test.miprimeraapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MemberService{

    @GET
    suspend fun getUser():UiName

    @GET
    suspend fun getUsers(@Query("amount") amount:Int):List<UiName>
}