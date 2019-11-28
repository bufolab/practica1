package com.test.miprimeraapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.miprimeraapp.data.DataRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val _state = MutableLiveData<UIMainState>()
    val state :LiveData<UIMainState>
    get() = _state

    fun onLogin(user:String){
        viewModelScope.launch(IO){
            //aqui empezamos a hacer nuestro trabajo en background gracias a lo corrutina
            //y además utilizando el pool IO ( si no, seguiriamos en el main thread!)

            //una llamada a servidor
            //o una llamada la base de datos
            //o un calculo intenso
            _state.postValue(UIMainState.Loading)
            Thread.sleep(500) //solo como ejemplo esperamos 500 millisegundos

            _state.postValue(UIMainState.UserLoginResult(Random.nextBoolean()))
        }
    }
}

sealed class UIMainState{
    object Loading:UIMainState()
    class  UserLoginResult(val success:Boolean):UIMainState()
}
