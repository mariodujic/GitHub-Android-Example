package com.groundzero.github.feature.authentication.api

import retrofit2.Response
import retrofit2.http.*

interface AuthenticationApi {

    @Headers("Accept: application/json")
    @POST
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Url baseUrl: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") oAuthCode: String,
        @Field("redirect_uri") redirectUrl: String
    ): Response<AuthenticationResponse>

}