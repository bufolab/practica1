package com.test.miprimeraapp.data.remote

import com.test.miprimeraapp.BuildConfig
import com.test.miprimeraapp.model.MemberModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class RemoteDataSource {

    var service: MemberService

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
        service = retrofit.create(MemberService::class.java)
    }


    suspend fun getAMember(): MemberModel {
        return service.getUser().toMemberModel()
    }

    suspend fun getMembers(amount: Int): List<MemberModel> {
        return service.getUsers(amount).map { it.toMemberModel() }
    }


}

fun UiName.toMemberModel(): MemberModel {
    return MemberModel(Random.nextLong(), this.name, this.surname)
}