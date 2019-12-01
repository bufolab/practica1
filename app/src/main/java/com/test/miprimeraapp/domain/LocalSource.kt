package com.test.miprimeraapp.domain

import com.test.miprimeraapp.model.PostModel

interface LocalSource{
    fun isEmpty():Boolean

    fun setPosts(posts:List<PostModel>)
    fun getPosts():List<PostModel>

    fun getPost(id: Long): PostModel?
    fun deletePost(id: Long)
}