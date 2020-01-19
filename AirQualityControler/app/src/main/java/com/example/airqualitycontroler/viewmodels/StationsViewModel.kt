package com.example.airqualitycontroler.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.airqualitycontroler.repositories.Repository
import kotlinx.coroutines.Dispatchers

class StationsViewModel : ViewModel() {
    val repository: Repository =
        Repository()

    val listOfStations = liveData(Dispatchers.IO) {
        val retrivedStations = repository.getAllStations()
        emit(retrivedStations)
    }
}
