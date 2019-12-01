package com.test.miprimeraapp

import android.app.Application
import com.test.miprimeraapp.data.PostRepository
import com.test.miprimeraapp.data.local.LocalDataSource
import com.test.miprimeraapp.data.remote.JsonPlaceholderService
import com.test.miprimeraapp.data.remote.PostResquest
import com.test.miprimeraapp.data.remote.RemoteDataSource
import com.test.miprimeraapp.domain.DataRepository
import com.test.miprimeraapp.domain.LocalSource
import com.test.miprimeraapp.domain.RemoteSource
import com.test.miprimeraapp.ui.contentlist.contentlist.SecondViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MiPrimeraAppApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        val dataModule = module {

            single{getService(BuildConfig.baseUrl)}
            single<LocalSource> { LocalDataSource() }
            single<RemoteSource> { RemoteDataSource(get()) }
            single<DataRepository> { PostRepository(get(),get()) }

            viewModel { SecondViewModel(get()) }
        }

        startKoin {
            androidContext(this@MiPrimeraAppApplication)
            // declare modules
            modules(dataModule)
        }

    }

    private fun getService(url:String): JsonPlaceholderService {
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
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()) //queremos que automaticamente parse los json
            .build()
        return retrofit.create(JsonPlaceholderService::class.java)
    }
}

