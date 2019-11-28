package com.test.miprimeraapp.data.remote

import com.test.miprimeraapp.BuildConfig
import com.test.miprimeraapp.model.PostModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class RemoteDataSource {

    var service: JsonPlaceholderService

    init {
        //añadimos logging
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        //creamos el servicio
        var retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()) //queremos que automaticamente parse los json
            .build()
        service = retrofit.create(JsonPlaceholderService::class.java)
    }

    suspend fun getPost(id:Long): PostModel {
        return service.getPost(id).toPostModel()
    }

    suspend fun getPosts(): List<PostModel> {
        return service.getPosts().map { it.toPostModel() }
    }

    suspend fun deletePost(id: Long) {
        return service.deletePost(id)
    }


}

fun PostResponse.toPostModel(): PostModel {
    return PostModel(this.id, this.title, this.body)
}