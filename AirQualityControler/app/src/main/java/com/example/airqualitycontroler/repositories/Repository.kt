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

    suspend fun getFavStationsWithSensors():List<StationWithSensors>
    {
        val stationsWithSensors = mutableListOf<StationWithSensors>()
        val sensorsInStations = mutableListOf<SensorsInStation>()

        try {
            val favId = favouriteIdDao.getFavouriteIds()

            val favStations =
                client.getAllStations().filter { it.id in favId.map { Item -> Item.favouriteId } }

            favStations.forEach { station ->
                val sensors = client.getAllSensors(station.id)
                val values = mutableListOf<Value>()
                sensors.forEach {
                    values.add(client.getAllValues(it.id))
                }
                stationsWithSensors.add(StationWithSensors(station, SensorsInStation(sensors, values)))
                sensorsInStations.add(SensorsInStation(sensors, values))
            }
        }catch(e:Exception){}

        return stationsWithSensors
    }
}