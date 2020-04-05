package com.groundzero.github.feature.authentication.api

import com.google.gson.annotations.SerializedName

data class AuthenticationResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("token_type")
    val tokenType: String
)