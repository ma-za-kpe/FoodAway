package com.kpe.foodaway.framework.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "success")
    val success: Boolean, // true
    @Json(name = "token")
    val token: String // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVmOGZkNmEzZGFmOTQwMTBjODIzMTdiZiIsImlhdCI6MTYwMzI2Mjc3OSwiZXhwIjoxNjA1ODU0Nzc5fQ.LsQQIBE-n0REtKTFqQ-y-MXhVEpiaBK1dnR9ORUobzQ
)