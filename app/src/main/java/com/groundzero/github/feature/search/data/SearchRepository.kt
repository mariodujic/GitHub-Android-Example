package com.groundzero.github.feature.search.data

import com.groundzero.github.data.deleteLiveData
import com.groundzero.github.data.resultLiveDataPersistent
import com.groundzero.github.data.updateLiveDataPersistent
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val dataSource: SearchDataSource,
    private val repositoryDao: RepositoryDao,
    private val pageDao: PageDao
) {
    fun searchQuery(query: String, page: Int) =
        resultLiveDataPersistent(
            networkCall = { dataSource.searchQuery(query, page) },
            saveLocal = { repositoryDao.insertRepositories(it.repositories) },
            observeLocal = { repositoryDao.getRepositories() }
        )

    fun updateQuery(query: String, page: Int) {
        updateLiveDataPersistent(
            networkCall = { dataSource.searchQuery(query, page) },
            saveLocal = { repositoryDao.insertRepositories(it.repositories) }
        )
    }

    fun deleteData() {
        deleteLiveData(
            deletePersistentData = { repositoryDao.deleteRepositories() }
        )
    }
}