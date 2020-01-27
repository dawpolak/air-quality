package com.example.airqualitycontroler.repositories

import com.example.airqualitycontroler.models.*
import java.net.SocketTimeoutException

class Repository(private val favouriteIdDao: FavouriteIdDao) {

    //API
    private var client: IWebservice = RetrofitClient.webservice


        suspend fun getAllStations() = try{client.getAllStations()}catch(e: Exception){listOf<Station>()}





    //Room
    suspend fun getAllFavId() = favouriteIdDao.getFavouriteIds()

    suspend fun insert(favouriteId: FavouriteId) {
        favouriteIdDao.insert(favouriteId)
    }
    suspend fun delete(favouriteId: FavouriteId) {
        favouriteIdDao.deleteFavId(favouriteId)
    }

    //hybrid
    //returns list of stations whose ids are in the databese (favouriteId)
    suspend  fun  getFavStations(): List<Station>
    {
        var stations = listOf<Station>()
        var favId = listOf<FavouriteId>()
        try {
            stations = client.getAllStations()
            favId = favouriteIdDao.getFavouriteIds()
        }catch (ste: Exception){}

        return stations.filter { it.id in favId.map { Item -> Item.favouriteId }}
    }

    //returns list of sensors with values from favourite stations (stations whose ids in the database)
    suspend  fun  getSensorsFromFavStation(): List<SensorsInStation>
    {
        val sensorsInStations = mutableListOf<SensorsInStation>()
        try {
            val favId = favouriteIdDao.getFavouriteIds()
            favId.forEach {
                val sensors = client.getAllSensors(it.favouriteId)
                val values = mutableListOf<Value>()
                sensors.forEach {
                    values.add(client.getAllValues(it.id))
                }
                sensorsInStations.add(SensorsInStation(sensors, values))
            }
        }catch(ste: Exception){}
        return sensorsInStations
    }
}