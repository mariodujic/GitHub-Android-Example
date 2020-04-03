package com.groundzero.github.feature.search.data

import androidx.paging.LivePagedListBuilder
import com.groundzero.github.data.resultLiveDataPersistent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val dataSource: SearchDataSource,
    private val repositoryDao: RepositoryDao
) {
    fun searchQuery(query: String, page: Int, perPage: Int) =
        resultLiveDataPersistent(
            networkCall = { dataSource.searchQuery(query, page, perPage) },
            saveLocal = { repositoryDao.insertRepositories(it.repositories) },
            observeLocal = { LivePagedListBuilder(repositoryDao.reposByName(), perPage).build() }
        )

    fun deleteData() = CoroutineScope(IO).launch {
        repositoryDao.deleteRepositories()
    }
}