package com.example.airqualitycontroler.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.airqualitycontroler.models.FavouriteId
import com.example.airqualitycontroler.models.FavouriteIdRoomDatabase
import com.example.airqualitycontroler.repositories.FavouriteIdRepository
import com.example.airqualitycontroler.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddStationViewModel(application: Application) : ViewModel() {
    val repository: Repository =
        Repository()

    val listOfStations = liveData(Dispatchers.IO) {
        val retrivedStations = repository.getAllStations()
        emit(retrivedStations)
    }


    private val favouriteIdrRepository: FavouriteIdRepository
    val allFavouriteId: LiveData<List<FavouriteId>>

    init {
        val favouriteIdDao = FavouriteIdRoomDatabase.getDatabase(application).favouriteIdDao()
        favouriteIdrRepository = FavouriteIdRepository(favouriteIdDao)
        allFavouriteId = favouriteIdrRepository.allFavouriteIds
    }

    fun insert(favouriteId: FavouriteId) = viewModelScope.launch {
        favouriteIdrRepository.insert(favouriteId)
    }
}
