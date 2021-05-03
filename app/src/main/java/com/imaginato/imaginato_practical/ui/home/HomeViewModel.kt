package com.imaginato.imaginato_practical.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.imaginato.imaginato_practical.data.randomuser.entity.RandomUserItem
import com.imaginato.imaginato_practical.domain.base.Result
import com.imaginato.imaginato_practical.domain.randomuser.usecase.FetchRandomUserUseCase
import com.imaginato.imaginato_practical.ui.base.BaseViewModel
import com.imaginato.imaginato_practical.ui.base.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchRandomUserUseCase: FetchRandomUserUseCase
) : BaseViewModel() {

    private val _randomUserLiveEvent = MutableLiveData<ArrayList<RandomUserItem>>(arrayListOf())
    val randomUserLiveEvent: LiveData<ArrayList<RandomUserItem>> = _randomUserLiveEvent
    
    private val _progressEvent = SingleLiveEvent<Boolean>()
    val progressEvent: SingleLiveEvent<Boolean> = _progressEvent

    private val _errorEvent = SingleLiveEvent<String>()
    val errorEvent: SingleLiveEvent<String> = _errorEvent

    private var page = 1


    fun getRandomUsers(resetPaging: Boolean = false) {
        if (resetPaging) {
            page = 1
        }
        if (_progressEvent.value == true) {
            return
        }
        _progressEvent.value = true
        fetchRandomUserUseCase.invoke(
            scope = viewModelScope,
            params = FetchRandomUserUseCase.Param(page)
        ) { result ->
            _progressEvent.value = false
            if (result is Result.Success) {
                if (page == 1) {
                    _randomUserLiveEvent.postValue(result.value.results)
                }else{
                    val value = _randomUserLiveEvent.value
                    value?.addAll(result.value.results)
                    _randomUserLiveEvent.postValue(value)

                }
                page += 1
            }else if(result is Result.Error){
                _errorEvent.value = result.error.message
            }
        }
    }

    fun updateUserFavorite(add: Boolean, user: RandomUserItem) {
        val value = _randomUserLiveEvent.value
        value?.forEach {
            if (it.email == user.email) {
                it.isFavorite = add
            }
        }
        _randomUserLiveEvent.value = value
    }
}
