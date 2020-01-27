package com.example.airqualitycontroler.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.airqualitycontroler.models.*
import com.example.airqualitycontroler.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StationsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    //for LiveData refresh using swipeRefreshLayout
    private val reloadTrigger = MutableLiveData<Boolean>()

    init {
        val favouriteIdsDao = FavouriteIdRoomDatabase.getDatabase(application).favouriteIdDao()
        repository = Repository(favouriteIdsDao)
        refreshUsers()
    }

    //for LiveData refresh using swipeRefreshLayout
    fun refreshUsers() {
        reloadTrigger.value = true
    }

    //LiveDatas!!!!!!!!
    val listOfSensors: LiveData<List<SensorsInStation>> = Transformations.switchMap(reloadTrigger) {
        liveData(Dispatchers.IO) {
            val retrivedSensors = repository.getSensorsFromFavStation()
        emit(retrivedSensors)
        }
    }


    val listOfFavStations: LiveData<List<Station>> = Transformations.switchMap(reloadTrigger) {
        liveData(Dispatchers.IO) {
               val retrivedStations = repository.getFavStations()
        emit(retrivedStations)
        }
    }
}
