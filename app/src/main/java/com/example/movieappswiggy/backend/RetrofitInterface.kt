package com.example.movieappswiggy.backend

import com.example.movieappswiggy.model.MovieResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("/")
     fun getSearchMovies(
        @Query("apikey") apikey: String,
        @Query("s") s: String
    ) : Call<MovieResponseModel?>

}