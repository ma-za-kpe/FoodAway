package com.kpe.foodaway.framework.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Login(
    @Json(name = "email")
    val email: String, // voom@gmail.com
    @Json(name = "password")
    val password: String // 123456
)