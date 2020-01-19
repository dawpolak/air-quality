package com.example.airqualitycontroler.models

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl("http://api.gios.gov.pl/pjp-api/rest/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(IWebservice::class.java)
    }
}