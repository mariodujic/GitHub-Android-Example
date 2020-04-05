package com.groundzero.github.feature.authentication.data

import com.groundzero.github.feature.authentication.api.AuthenticationResponse

object AccessTokenDto {
    fun fromResponse(response: AuthenticationResponse) = AccessToken(response.accessToken)
}