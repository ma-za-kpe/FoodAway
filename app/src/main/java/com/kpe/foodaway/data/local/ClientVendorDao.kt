package com.kpe.foodaway.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ClientVendorDao {
    @Query("SELECT * from cv_table")
    fun getAlphabetizedWords(): LiveData<List<ClientVendor>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: ClientVendor)

    @Query("DELETE FROM cv_table")
    suspend fun deleteAll()
}