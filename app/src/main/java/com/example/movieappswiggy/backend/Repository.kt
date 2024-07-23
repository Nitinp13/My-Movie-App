package com.example.movieappswiggy.backend

import com.example.movieappswiggy.model.MovieResponseModel

class Repository {

   suspend fun getSearchedMovies(query : String?, onComplete : ((List<MovieResponseModel.MovieItem?>?) -> Unit)? = null){
        if (!query.isNullOrEmpty()){
            try {
                val rt = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface::class.java)
               val response = rt.getSearchMovies(apikey = AppConfig.API_KEY, s = query ?: "").execute()
                if (response.isSuccessful){
                    if (!response.body()?.search.isNullOrEmpty()){
                        onComplete?.invoke(response.body()?.search)
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()

            }
        }

    }
}