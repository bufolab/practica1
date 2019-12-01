package com.test.miprimeraapp.data.local

import com.test.miprimeraapp.domain.LocalSource
import com.test.miprimeraapp.model.PostModel

/**
 * Clase simple que hace de cache.
 * La implementacion podria persistencia en base de datos.
 */
class LocalDataSource:LocalSource{

    private var cachePost:MutableList<PostModel> = mutableListOf()

    override fun isEmpty():Boolean = cachePost.isEmpty()

    override fun setPosts(posts:List<PostModel>){
        cachePost.clear()
        cachePost.addAll(posts)
    }

    override fun getPosts():List<PostModel> = cachePost.toList() //copy list, evita problemas

    override fun getPost(id: Long): PostModel? = cachePost.firstOrNull { it.id == id }
    override fun deletePost(id: Long) {
        val filter = cachePost.filter { it.id != id }
        setPosts(filter)
    }

}