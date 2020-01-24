package com.example.airqualitycontroler.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete



@Dao
interface FavouriteIdDao {
    @Query("SELECT * from favouriteId_table ORDER BY id")
    fun getFavouriteIds(): List<FavouriteId>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(id: FavouriteId)

    @Query("DELETE FROM favouriteId_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteFavId(favId: FavouriteId)
}