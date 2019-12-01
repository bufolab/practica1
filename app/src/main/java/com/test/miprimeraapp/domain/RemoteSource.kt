package com.test.miprimeraapp.domain

import com.test.miprimeraapp.data.remote.toPostModel
import com.test.miprimeraapp.model.PostModel

interface RemoteSource{
    suspend fun getPost(id:Long): PostModel

    suspend fun getPosts(): List<PostModel>

    suspend fun deletePost(id: Long)
}