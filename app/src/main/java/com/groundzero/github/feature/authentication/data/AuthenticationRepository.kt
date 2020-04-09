package com.groundzero.github.feature.authentication.data

import androidx.lifecycle.LiveData
import com.groundzero.github.data.resultLiveDataPersistent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
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
    ) = resultLiveDataPersistent(
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

    fun deleteAccessToken() = CoroutineScope(IO).launch {
        dao.deleteAccessToken()
    }
}