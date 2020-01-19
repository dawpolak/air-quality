package com.example.airqualitycontroler.models

import retrofit2.http.GET

interface IWebservice {

    @GET("station/findAll")
    suspend fun getAllStations(): List<Station>
}