package com.betulnecanli.dominantcolorbackground.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betulnecanli.dominantcolorbackground.model.Result
import com.betulnecanli.dominantcolorbackground.repository.ArtistRepo
import com.betulnecanli.dominantcolorbackground.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val repository : ArtistRepo ) : ViewModel(){


        private val _list = MutableLiveData<Resource<Result>>()

        val list : LiveData<Resource<Result>>
            get() = _list


    init {
        getList()
    }


    private fun getList() = viewModelScope.launch {
        _list.postValue(Resource.loading(null))
        repository.getList().let {
            if(it.isSuccessful){
                _list.postValue(Resource.success(it.body()))

            }
            else{
                _list.postValue(Resource.error(it.errorBody().toString(),null))
            }
        }



    }
}