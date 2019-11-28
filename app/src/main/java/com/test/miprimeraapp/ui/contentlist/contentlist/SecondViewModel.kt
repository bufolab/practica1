package com.test.miprimeraapp.ui.contentlist.contentlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.miprimeraapp.data.MembersRepository
import com.test.miprimeraapp.model.MemberModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SecondViewModel : ViewModel() {
    private val _state = MutableLiveData<UISecondState>()
    val state: LiveData<UISecondState>
        get() = _state

    fun onLoadMembers(amount: String) {
        viewModelScope.launch(IO) {
            //aqui empezamos a hacer nuestro trabajo en background gracias a lo corrutina
            //y además utilizando el pool IO ( si no, seguiriamos en el main thread!)

            //una llamada a servidor
            //o una llamada la base de datos
            //o un calculo intenso
            _state.postValue(UISecondState.Loading)

            val amount = amount.toIntOrNull() ?: 1

            val members = if (amount > 1) {
                MembersRepository.getMembers()
            } else {
                listOf(MembersRepository.getAMember())
            }

            _state.postValue(UISecondState.MembersResult(members))
        }
    }
}

sealed class UISecondState {
    object Loading : UISecondState()
    class MembersResult(val members: List<MemberModel>) : UISecondState()
}
