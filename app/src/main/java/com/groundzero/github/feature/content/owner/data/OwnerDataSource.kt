package com.groundzero.github.feature.content.owner.data

import com.groundzero.github.data.BaseDataSource
import com.groundzero.github.feature.content.owner.api.OwnerApi
import javax.inject.Inject

class OwnerDataSource @Inject constructor(private val api: OwnerApi) : BaseDataSource() {
    suspend fun getOwner(username: String) = getResult { api.getRepositoryOwner(username) }
}