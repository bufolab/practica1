package com.test.miprimeraapp.dagger

import com.test.miprimeraapp.BuildConfig
import com.test.miprimeraapp.data.PostRepository
import com.test.miprimeraapp.data.local.LocalDataSource
import com.test.miprimeraapp.data.remote.JsonPlaceholderService
import com.test.miprimeraapp.data.remote.RemoteDataSource
import com.test.miprimeraapp.domain.DataRepository
import com.test.miprimeraapp.domain.LocalSource
import com.test.miprimeraapp.domain.RemoteSource
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class DataModule{

    @Provides
    fun DataRepositoryProvider(remoteDataSource:RemoteDataSource,localDataSource:LocalDataSource): DataRepository {
        return PostRepository(remoteDataSource,localDataSource)
    }

    @Provides
    fun LocalSourceProvider(): LocalSource {
        return LocalDataSource()
    }

    @Provides
    fun RemoteSourceProvider(jsonPlaceholderService:JsonPlaceholderService): RemoteSource {
        return RemoteDataSource(jsonPlaceholderService)
    }

    @Provides
    fun JsonPlaceholderServiceProvider():JsonPlaceholderService{
        //añadimos logging
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
        {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else
        {
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
        return retrofit.create(JsonPlaceholderService::class.java)
    }
}