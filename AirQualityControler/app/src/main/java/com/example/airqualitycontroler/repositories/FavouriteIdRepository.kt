package com.example.airqualitycontroler.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.airqualitycontroler.models.FavouriteId
import com.example.airqualitycontroler.models.FavouriteIdDao

class FavouriteIdRepository(private val favouriteIdDao: FavouriteIdDao) {

    val allFavouriteIds: LiveData<List<FavouriteId>> = MutableLiveData(favouriteIdDao.getFavouriteIds())

    suspend fun insert(favouriteId: FavouriteId) {
        favouriteIdDao.insert(favouriteId)
    }
}