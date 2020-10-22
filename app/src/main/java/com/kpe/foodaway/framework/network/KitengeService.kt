package com.kpe.foodaway.framework.network

import com.kpe.foodaway.framework.model.remote.Login
import com.kpe.foodaway.framework.model.remote.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface KitengeService {
    @Headers("Content-Type: application/json")
    @POST("api/v1/auth/login")
    fun login(@Body body: Login): Deferred<Response<LoginResponse>>
}