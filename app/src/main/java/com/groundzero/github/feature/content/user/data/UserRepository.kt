package com.groundzero.github.feature.content.user.data

import com.groundzero.github.data.resultLiveDataPersistent
import com.groundzero.github.feature.authentication.data.AccessTokenDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val dataSource: UserDataSource,
    private val accessTokenDao: AccessTokenDao,
    private val userDao: UserDao
) {
    fun getUser() = resultLiveDataPersistent(
        networkCall = { dataSource.getUser(accessTokenDao.getAccessTokenSync()?.token ?: "") },
        saveLocal = { userDao.insertUser(UserDto.fromResponse(it)) },
        observeLocal = { userDao.getUser() }
    )

    fun deleteUser() = CoroutineScope(IO).launch {
        userDao.deleteUser()
    }

    fun deleteAccessToken() = CoroutineScope(IO).launch {
        accessTokenDao.deleteAccessToken()
    }
}