package com.groundzero.github.feature.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.groundzero.github.feature.authentication.data.AccessToken
import com.groundzero.github.feature.authentication.data.AccessTokenDao
import javax.inject.Inject

class SplashViewModel @Inject constructor(dao: AccessTokenDao) : ViewModel() {
    val accessTokenLive: LiveData<AccessToken> = dao.getAccessToken()
}