package com.test.miprimeraapp.data

import com.test.miprimeraapp.data.remote.RemoteDataSource
import com.test.miprimeraapp.model.PostModel

object PostRepository {

    val remoteDataSource = RemoteDataSource()

   suspend fun getPost(id:Long):PostModel{
        //aqui deberiamos checkear una base de datos local o cache
        //pero a modo ilustrativo solo accedemos al repositorio remoto
        return remoteDataSource.getPost(id)
    }

    suspend fun getPosts():List<PostModel>{
        return remoteDataSource.getPosts()
    }

    suspend fun deletePost(id: Long) {
        remoteDataSource.deletePost(id)
    }
}