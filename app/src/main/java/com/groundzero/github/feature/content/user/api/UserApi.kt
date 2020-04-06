package com.groundzero.github.feature.content.user.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {

    @GET("/user")
    suspend fun getUser(
        @Header("Authorization") bearerToken: String
    ): Response<UserResponse>
}