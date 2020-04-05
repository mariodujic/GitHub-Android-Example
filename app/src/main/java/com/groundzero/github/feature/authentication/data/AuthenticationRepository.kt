package com.groundzero.github.feature.authentication.data

import androidx.lifecycle.LiveData
import com.groundzero.github.data.Result
import com.groundzero.github.data.resultLiveDataPersistent
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val dataSource: AuthenticationDataSource,
    private val dao: AccessTokenDao
) {

    fun getAccessToken(
        clientId: String,
        clientSecret: String,
        oAuthCode: String,
        redirectUrl: String
    ): LiveData<Result<AccessToken>> = resultLiveDataPersistent(
        networkCall = {
            dataSource.getAccessToken(
                clientId,
                clientSecret,
                oAuthCode,
                redirectUrl
            )
        },
        saveLocal = { dao.insertAccessToken(AccessTokenDto.fromResponse(it)) },
        observeLocal = { dao.getAccessToken() }
    )
}