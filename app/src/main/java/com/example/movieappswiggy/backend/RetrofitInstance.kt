package com.example.movieappswiggy.backend

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
   lateinit var retrofit: Retrofit

    fun getRetrofitInstance(): Retrofit {
        if (this::retrofit.isInitialized){
           return  retrofit
        }else{
            retrofit = Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }
    }
}