package com.groundzero.github.feature.authentication.ui

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.groundzero.github.BuildConfig
import com.groundzero.github.feature.authentication.data.AuthenticationRepository
import javax.inject.Inject

class AuthenticationViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel() {

    private val clientId = BuildConfig.CLIENT_ID
    private val clientSecret = BuildConfig.CLIENT_SECRET

    fun getOAuthUrl(redirectUrl: String) =
        "https://github.com/login/oauth/authorize?client_id=$clientId&redirect_uri=$redirectUrl"

    fun verifyOAuthResponse(uri: Uri?, startsWith: String): Boolean =
        uri != null && uri.toString().startsWith(startsWith)

    fun getCode(uri: Uri) = uri.getQueryParameter("code")

    fun getAccessToken(
        oAuthCode: String,
        redirectUrl: String
    ) = repository.getAccessToken(clientId, clientSecret, oAuthCode, redirectUrl)

    fun removeAccessToken() = repository.deleteAccessToken()
}