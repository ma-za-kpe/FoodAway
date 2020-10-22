package com.kpe.foodaway.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kpe.foodaway.framework.KitengeRepository
import com.kpe.foodaway.framework.model.local.ClientVendor
import com.kpe.foodaway.framework.model.local.ClientVendorRepository
import com.kpe.foodaway.framework.model.local.KitengeDB
import com.kpe.foodaway.framework.model.remote.Login
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: KitengeRepository) :
    ViewModel() {

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.

    init {

    }

    suspend fun login(){
        Timber.d("email " + email)
        Timber.d("password " + password)
        val user = Login("m@gmail.com","123455")
        repository.loginUser(user)
    }


    //ROOM

//    /**
//     * Launching a new coroutine to insert the data in a non-blocking way
//     */
//    suspend fun insert(clientVendor: ClientVendor)  {
//        repository.insert(clientVendor)
//    }
//
//    /**
//     * Launching a new coroutine to delete the data in a non-blocking way
//     */
//    suspend fun delete(){
//        repository.delete()
//    }
}