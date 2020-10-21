package com.kpe.foodaway.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kpe.foodaway.data.local.ClientVendor
import com.kpe.foodaway.data.local.ClientVendorRepository
import com.kpe.foodaway.data.local.KitengeDB

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ClientVendorRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.

    init {
        val clientVendorDao = KitengeDB.getDatabase(application).clientVendorDao()
        repository = ClientVendorRepository(clientVendorDao)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    suspend fun insert(clientVendor: ClientVendor)  {
        repository.insert(clientVendor)
    }

    /**
     * Launching a new coroutine to delete the data in a non-blocking way
     */
    suspend fun delete(){
        repository.delete()
    }
}