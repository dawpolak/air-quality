package com.example.airqualitycontroler.models

import retrofit2.http.GET
import retrofit2.http.Path

interface IWebservice {

    @GET("station/findAll")
    suspend fun getAllStations(): List<Station>

    @GET("station/sensors/{stationId}")
    suspend fun getAllSensors(@Path(value = "stationId") stationId: Int): List<Sensor>

    @GET("data/getData/{sensorId}")
    suspend fun getAllValues(@Path(value = "sensorId") sensorId: Int): Value
}