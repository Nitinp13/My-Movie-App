package com.example.movieappswiggy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappswiggy.backend.Repository
import com.example.movieappswiggy.model.MovieResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Query

class MainViewModel : ViewModel() {

    private val _data : MutableLiveData<MutableList<MovieResponseModel.MovieItem?>> = MutableLiveData(null)
    val data : LiveData<MutableList<MovieResponseModel.MovieItem?>> = _data

    private val _loaddata : MutableLiveData<MutableList<MovieResponseModel.MovieItem?>> = MutableLiveData(null)
    val loaddata : LiveData<MutableList<MovieResponseModel.MovieItem?>> = _loaddata
    val repository = Repository()

    fun getSearchedMovie(query: String? = null){
        try {
            if (!query.isNullOrEmpty()){
                viewModelScope.launch(Dispatchers.IO) {
                    repository.getSearchedMovies(query = query) {
                        if (!it.isNullOrEmpty()){
                            _data.postValue(it.toMutableList())
                        }

                    }
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }



    }

    fun getMoreSearchedMovie(query: String? = null){
        try {
            if (!query.isNullOrEmpty()){
                viewModelScope.launch(Dispatchers.IO) {
                    repository.getSearchedMovies(query = query) {
                        if (!it.isNullOrEmpty()){
                            _loaddata.postValue(it.toMutableList())
                        }

                    }
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }



    }
}