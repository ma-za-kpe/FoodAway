package com.kpe.foodaway.framework.model.local

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ClientVendorRepository(private val clientVendorDao: ClientVendorDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allClientVendor: LiveData<List<ClientVendor>> = clientVendorDao.getAlphabetizedWords()

    suspend fun insert(word: ClientVendor?) {
        word?.let { clientVendorDao.insert(it) }
    }

    suspend fun delete() {
        clientVendorDao.deleteAll()
    }
}