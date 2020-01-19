package com.example.airqualitycontroler.repositories

import com.example.airqualitycontroler.models.IWebservice
import com.example.airqualitycontroler.models.RetrofitClient

class Repository {
    private var client: IWebservice = RetrofitClient.webservice

    suspend fun getAllStations() = client.getAllStations()
}