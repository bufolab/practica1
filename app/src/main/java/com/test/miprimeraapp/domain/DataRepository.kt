package com.test.miprimeraapp.domain

import com.test.miprimeraapp.data.PostRepository
import com.test.miprimeraapp.model.PostModel

interface DataRepository {
    suspend fun getPost(id:Long): PostModel?

    suspend fun getPosts():List<PostModel>

    suspend fun deletePost(id: Long)
}