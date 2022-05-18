package com.example.inviofilm.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inviofilm.model.FilmResponseModel
import com.example.inviofilm.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val _filmData = MutableLiveData<FilmResponseModel?>()
    val filmData: LiveData<FilmResponseModel?>
        get() = _filmData

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    fun getFilm(name: String) {
        _loading.value = true
        viewModelScope.launch {
            val request = homeRepository.getFilmData(name)
            when (request) {
                is NetworkResult.Success -> {
                    if (request.data?.title==null){
                        _error.value=true
                        _loading.value = false
                    }else{
                        _filmData.value = request.data
                        _loading.value = false
                        _error.value=false
                    }

                }
                is NetworkResult.Error -> {
                    _error.value = true
                    _loading.value = false
                }
            }
        }
    }
}