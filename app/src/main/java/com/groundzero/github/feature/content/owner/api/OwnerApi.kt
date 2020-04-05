package com.groundzero.github.feature.content.owner.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OwnerApi {

    @GET("/users/{username}")
    suspend fun getRepositoryOwner(
        @Path("username") username: String
    ): Response<OwnerResponse>
}