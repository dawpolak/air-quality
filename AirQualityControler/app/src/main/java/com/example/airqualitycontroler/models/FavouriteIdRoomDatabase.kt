package com.example.airqualitycontroler.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FavouriteId::class), version = 1, exportSchema = false)
public abstract class FavouriteIdRoomDatabase : RoomDatabase() {
    abstract fun favouriteIdDao(): FavouriteIdDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FavouriteIdRoomDatabase? = null

        fun getDatabase(context: Context): FavouriteIdRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavouriteIdRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}