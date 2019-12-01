package com.test.miprimeraapp.dagger

import com.test.miprimeraapp.data.PostRepository
import com.test.miprimeraapp.data.local.LocalDataSource
import com.test.miprimeraapp.data.remote.RemoteDataSource
import com.test.miprimeraapp.domain.DataRepository
import com.test.miprimeraapp.ui.contentlist.contentlist.SecondViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules=[DataModule::class])
interface MiPrimeraAppComponent{
    fun inject(source: RemoteDataSource)
    fun inject(source: PostRepository)
    fun inject(secondViewModel: SecondViewModel)

    val dataRepository:DataRepository

}
