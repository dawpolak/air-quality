package com.example.airqualitycontroler.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.airqualitycontroler.models.*

class Repository(private val favouriteIdDao: FavouriteIdDao) {

    //API
    private var client: IWebservice = RetrofitClient.webservice
    suspend fun getAllStations() = client.getAllStations()


    //Room
    suspend fun getAllFavId() = favouriteIdDao.getFavouriteIds()
    suspend fun insert(favouriteId: FavouriteId) {
        favouriteIdDao.insert(favouriteId)
    }
    suspend fun delete(favouriteId: FavouriteId) {
        favouriteIdDao.deleteFavId(favouriteId)
    }

    suspend  fun  getFavStations(): List<Station>
    {
        val stations = client.getAllStations()
        val favId = favouriteIdDao.getFavouriteIds()

        return stations.filter { it.id in favId.map { Item -> Item.favouriteId }}
    }

}