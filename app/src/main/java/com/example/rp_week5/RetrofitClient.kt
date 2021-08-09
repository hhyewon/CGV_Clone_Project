package com.example.rp_week5

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val sRetrofit = initRetrofit()
    private const val MOVIE_URL = "https://api.themoviedb.org/3/"

    private fun initRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(MOVIE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}