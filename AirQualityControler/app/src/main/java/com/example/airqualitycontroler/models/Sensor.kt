package com.example.airqualitycontroler.models
import com.google.gson.annotations.SerializedName

data class SensorsInStation(
    @SerializedName("listOfSensors")
    val listOfSensors: List<Sensor>,
    @SerializedName("listOfValues")
    val listOfValues: List<Value>

)


data class Sensor(
    @SerializedName("id")
    val id: Int,
    @SerializedName("param")
    val `param`: Param,
    @SerializedName("stationId")
    val stationId: Int
)

data class Param(
    @SerializedName("idParam")
    val idParam: Int,
    @SerializedName("paramCode")
    val paramCode: String,
    @SerializedName("paramFormula")
    val paramFormula: String,
    @SerializedName("paramName")
    val paramName: String
)

data class Value(
    @SerializedName("key")
    val key: String,
    @SerializedName("values")
    val values: List<ValueX>
)

data class ValueX(
    @SerializedName("date")
    val date: String,
    @SerializedName("value")
    val value: Double
)