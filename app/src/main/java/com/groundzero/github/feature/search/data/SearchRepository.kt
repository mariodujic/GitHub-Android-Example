package com.groundzero.github.feature.search.data

import com.groundzero.github.data.resultLiveDataPersistant
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val dataSource: SearchDataSource,
    private val dao: RepositoryDao
) {
    fun searchQuery(query: String, page: Int) =
        resultLiveDataPersistant(
            networkCall = { dataSource.searchQuery(query, page) },
            saveLocal = { dao.insertRepositories(it.repositories) },
            observeLocal = { dao.getRepositories() }
        )
}