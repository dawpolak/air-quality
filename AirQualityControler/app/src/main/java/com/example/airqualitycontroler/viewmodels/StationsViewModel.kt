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

    private val reloadTrigger = MutableLiveData<Boolean>()

    init {
        val favouriteIdsDao = FavouriteIdRoomDatabase.getDatabase(application).favouriteIdDao()
        repository = Repository(favouriteIdsDao)
        refreshUsers()
    }
    fun refreshUsers() {
        reloadTrigger.value = true
    }

    //api
//    val listOfStations = liveData(Dispatchers.IO) {
//        val retrivedStations = repository.getAllStations()
//        emit(retrivedStations)
//    }

//    val listOfSensors = liveData(Dispatchers.IO) {
//        Log.d("dupa","retrivedStations:"+repository.getSensorsFromFavStation().toString())
//        val retrivedStations = repository.getSensorsFromFavStation()
//        emit(retrivedStations)
//    }
    val listOfSensors: LiveData<List<SensorsInStation>> = Transformations.switchMap(reloadTrigger) {
        liveData(Dispatchers.IO) {
            val retrivedStations = repository.getSensorsFromFavStation()
       emit(retrivedStations)
        }
    }

    //baza


//    val listOfFavStations = liveData(Dispatchers.IO) {
//        val retrivedFavId = repository.getFavStations()
//        Log.d("dupa","viewModel: "+repository.getFavStations().toString())
//        emit(retrivedFavId)
//    }
    val listOfFavStations: LiveData<List<Station>> = Transformations.switchMap(reloadTrigger) {
    liveData(Dispatchers.IO) {
               val retrivedFavId = repository.getFavStations()
        emit(retrivedFavId)
    }
    }
}
