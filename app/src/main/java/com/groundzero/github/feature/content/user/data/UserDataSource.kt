package com.groundzero.github.feature.content.user.data

import com.groundzero.github.data.BaseDataSource
import com.groundzero.github.feature.content.user.api.UserApi
import javax.inject.Inject

class UserDataSource @Inject constructor(private val api: UserApi) : BaseDataSource() {
    suspend fun getUser(accessToken: String) = getResult { api.getUser("Bearer $accessToken") }
}