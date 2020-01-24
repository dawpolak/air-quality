package com.example.airqualitycontroler.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.airqualitycontroler.models.FavouriteId
import com.example.airqualitycontroler.models.FavouriteIdRoomDatabase
import com.example.airqualitycontroler.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StationsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    init {
        val favouriteIdsDao = FavouriteIdRoomDatabase.getDatabase(application).favouriteIdDao()
        repository = Repository(favouriteIdsDao)
    }

    //api
    val listOfStations = liveData(Dispatchers.IO) {
        val retrivedStations = repository.getAllStations()
        emit(retrivedStations)
    }

    //baza


    val listOfFavStations = liveData(Dispatchers.IO) {
        val retrivedFavId = repository.getFavStations()
        Log.d("dupa","viewModel: "+repository.getFavStations().toString())
        emit(retrivedFavId)
    }
}
