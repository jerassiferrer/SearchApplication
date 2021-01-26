package com.jera.searchapplication.valueobject

import com.google.gson.GsonBuilder
import com.jera.searchapplication.domain.WebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val webService by lazy {
        Retrofit.Builder()
            .baseUrl("https://itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory
                .create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}