package com.kpe.foodaway.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(ClientVendor::class), version = 1, exportSchema = false)
public abstract class KitengeDB : RoomDatabase() {

    abstract fun clientVendorDao(): ClientVendorDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: KitengeDB? = null

        fun getDatabase(context: Context): KitengeDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KitengeDB::class.java,
                    "kitenge_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}