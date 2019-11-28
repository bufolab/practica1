package com.test.miprimeraapp.data

import com.test.miprimeraapp.data.local.LocalDataSource
import com.test.miprimeraapp.data.remote.RemoteDataSource
import com.test.miprimeraapp.model.PostModel

object PostRepository {

    val remoteDataSource = RemoteDataSource()
    val localDataSource = LocalDataSource()

   suspend fun getPost(id:Long):PostModel?{
        //aqui deberiamos checkear una base de datos local o cache
        //pero a modo ilustrativo solo accedemos al repositorio remoto
       var posts = localDataSource.getPost(id)
       if(posts == null) {
           //deberiamos controlar error de que no exista en servidor
           posts = remoteDataSource.getPost(id)
           localDataSource.setPosts(listOf(posts))
       }

       return localDataSource.getPost(id)
    }

    suspend fun getPosts():List<PostModel>{
        if(localDataSource.isEmpty()) {
            //deberiamos controlar error de que no exista en servidor
            val posts = remoteDataSource.getPosts()
            localDataSource.setPosts(posts)
        }

        return localDataSource.getPosts()
    }

    suspend fun deletePost(id: Long) {
        localDataSource.deletePost(id)
        remoteDataSource.deletePost(id)
    }
}