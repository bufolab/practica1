package com.test.miprimeraapp.ui.contentlist.contentlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.miprimeraapp.MiPrimeraAppApplication
import com.test.miprimeraapp.data.PostRepository
import com.test.miprimeraapp.domain.DataRepository
import com.test.miprimeraapp.model.PostModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class SecondViewModel : ViewModel() {
    private val _state = MutableLiveData<UISecondState>()
    val state: LiveData<UISecondState>
        get() = _state

    @Inject
   lateinit var repository:DataRepository

    init {
        MiPrimeraAppApplication.component.inject(this)
    }
    fun onLoadPosts(paramId: String) {
        viewModelScope.launch(IO){
            //aqui empezamos a hacer nuestro trabajo en background gracias a lo corrutina
            //y además utilizando el pool IO ( si no, seguiriamos en el main thread!)

            //una llamada a servidor
            //o una llamada la base de datos
            //o un calculo intenso
            _state.postValue(UISecondState.Loading)

            val id = paramId.toLongOrNull() ?: 10

            val posts = if (id > 1) {
                repository.getPosts()
            } else {
                repository.getPost(id)?.run { listOf(this) }?: emptyList()
            }

            _state.postValue(UISecondState.GetPostResult(posts))
        }
    }

    fun deleteItem(id: Long) {
        viewModelScope.launch(IO) {
            _state.postValue(UISecondState.Loading)
            try {
                repository.deletePost(id)
                _state.postValue(UISecondState.DeletePostResult(true))
            } catch (e: Exception) {
                _state.postValue(UISecondState.DeletePostResult(false))
            }
        }
    }

}

sealed class UISecondState {
    object Loading : UISecondState()
    class GetPostResult(val posts: List<PostModel>) : UISecondState()
    class DeletePostResult(val success: Boolean) : UISecondState()
}
