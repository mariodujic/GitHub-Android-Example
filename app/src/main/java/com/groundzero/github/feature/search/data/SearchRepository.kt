package com.groundzero.github.feature.search.data

import com.groundzero.github.data.resultLiveDataPersistent
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val dataSource: SearchDataSource,
    private val dao: RepositoryDao
) {
    fun searchQueryWithDelete(query: String, page: Int, restartList: Boolean) =
        resultLiveDataPersistent(
            networkCall = { dataSource.searchQuery(query, page) },
            saveLocal = { dao.insertRepositories(it.repositories) },
            observeLocal = { dao.getRepositories() },
            removeLocal = { if (restartList) dao.deleteRepositories() }
        )
}