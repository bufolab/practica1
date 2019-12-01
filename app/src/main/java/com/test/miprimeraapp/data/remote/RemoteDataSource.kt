package com.test.miprimeraapp.data.remote

import com.test.miprimeraapp.BuildConfig
import com.test.miprimeraapp.domain.RemoteSource
import com.test.miprimeraapp.model.PostModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class RemoteDataSource constructor(var service: JsonPlaceholderService):RemoteSource{

    override suspend fun getPost(id:Long): PostModel {
        return service.getPost(id).toPostModel()
    }

    override suspend fun getPosts(): List<PostModel> {
        return service.getPosts().map { it.toPostModel() }
    }

    override suspend fun deletePost(id: Long) {
        return service.deletePost(id)
    }


}

fun PostResponse.toPostModel(): PostModel {
    return PostModel(this.id, this.title, this.body)
}