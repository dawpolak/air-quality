package com.example.airqualitycontroler.models
import com.google.gson.annotations.SerializedName

data class StationWithSensors(
    @SerializedName("station")
    val station: Station,
    @SerializedName("sensorsInStation")
    val sensorsInStation: SensorsInStation
)

data class Station(
    @SerializedName("addressStreet")
    val addressStreet: Any,
    @SerializedName("city")
    val city: City,
    @SerializedName("gegrLat")
    val gegrLat: String,
    @SerializedName("gegrLon")
    val gegrLon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("stationName")
    val stationName: String
)

data class City(
    @SerializedName("commune")
    val commune: Commune,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

data class Commune(
    @SerializedName("communeName")
    val communeName: String,
    @SerializedName("districtName")
    val districtName: String,
    @SerializedName("provinceName")
    val provinceName: String
)