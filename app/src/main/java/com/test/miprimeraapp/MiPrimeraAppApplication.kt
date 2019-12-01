package com.test.miprimeraapp

import android.app.Application
import com.test.miprimeraapp.dagger.DaggerMiPrimeraAppComponent
import com.test.miprimeraapp.dagger.DataModule
import com.test.miprimeraapp.dagger.MiPrimeraAppComponent

class MiPrimeraAppApplication : Application(){


    companion object{

    lateinit var component:  MiPrimeraAppComponent
    }
    override fun onCreate() {
        super.onCreate()
        component=  DaggerMiPrimeraAppComponent
            .builder()
            .dataModule(DataModule())
            .build()
    }
}

