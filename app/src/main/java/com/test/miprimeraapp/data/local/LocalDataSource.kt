package com.test.miprimeraapp.data.local

import com.test.miprimeraapp.model.PostModel

/**
 * Clase simple que hace de cache.
 * La implementacion podria persistencia en base de datos.
 */
class LocalDataSource {

    private var cachePost:MutableList<PostModel> = mutableListOf()

    fun isEmpty():Boolean = cachePost.isEmpty()

    fun setPosts(posts:List<PostModel>){
        cachePost.clear()
        cachePost.addAll(posts)
    }

    fun getPosts():List<PostModel> = cachePost.toList() //copy list, evita problemas

    fun getPost(id: Long): PostModel? = cachePost.firstOrNull { it.id == id }
    fun deletePost(id: Long) {
        val filter = cachePost.filter { it.id != id }
        setPosts(filter)
    }

}