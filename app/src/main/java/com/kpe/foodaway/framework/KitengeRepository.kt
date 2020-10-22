package com.kpe.foodaway.framework

import android.util.Log
import com.kpe.foodaway.framework.model.remote.Login
import com.kpe.foodaway.framework.network.KitengeService
import timber.log.Timber
import javax.inject.Inject

class KitengeRepository @Inject constructor(private val kitengeService: KitengeService) {

    suspend fun loginUser(login: Login): String {
        Timber.d("loging in user")
        val deferredResponse = kitengeService.login(login).await()

        return if (deferredResponse.isSuccessful) ({
            Timber.d("loging in user " +  deferredResponse.body()?.success)
            deferredResponse.body()?.token
        }).toString() else {
            throw Exception()
        }
    }
}