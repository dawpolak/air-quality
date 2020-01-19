package com.example.airqualitycontroler.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favouriteId_table")
class FavouriteId(@PrimaryKey @ColumnInfo(name = "id") val favouriteId: Int) {
}