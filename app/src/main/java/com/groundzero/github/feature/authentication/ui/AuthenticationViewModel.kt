package com.groundzero.github.feature.authentication.ui

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.groundzero.github.feature.authentication.data.AuthenticationRepository
import javax.inject.Inject

class AuthenticationViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel() {

    fun getOAuthUrl(clientId: String, redirectUrl: String) =
        "https://github.com/login/oauth/authorize?client_id=$clientId&redirect_uri=$redirectUrl"

    fun verifyOAuthResponse(uri: Uri?, startsWith: String): Boolean =
        uri != null && uri.toString().startsWith(startsWith)

    fun getCode(uri: Uri) = uri.getQueryParameter("code")

    fun getAccessToken(
        clientId: String,
        clientSecret: String,
        oAuthCode: String,
        redirectUrl: String
    ) = repository.getAccessToken(clientId, clientSecret, oAuthCode, redirectUrl)
}