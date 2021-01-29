package com.jera.searchapplication.viewmodel

import androidx.lifecycle.*
import com.jera.searchapplication.domain.Repo
import com.jera.searchapplication.valueobject.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class SearchViewModel(private val repo: Repo) : ViewModel() {

     val  searchData = MutableLiveData<String>()

    fun setSearchData(artistName : String){
        searchData.value = artistName
    }

    val fetchTracksList = searchData.distinctUntilChanged().switchMap { artistName ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getTracks(artistName))
            }catch (exception : Exception){
                emit(Resource.Failure<Exception>(exception))
            }
        }
    }


}