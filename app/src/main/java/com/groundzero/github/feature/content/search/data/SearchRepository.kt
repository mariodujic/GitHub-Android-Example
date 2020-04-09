package com.groundzero.github.feature.content.search.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.groundzero.github.data.resultLiveDataPersistent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val dataSource: SearchDataSource,
    private val repositoryDao: RepositoryDao
) {
    fun searchQuery(
        query: String,
        page: Int,
        perPage: Int,
        sortType: SortType
    ) = resultLiveDataPersistent(
            networkCall = { dataSource.searchQuery(query, page, perPage, sortType) },
            saveLocal = { repositoryDao.insertRepositories(it.repositories) },
            observeLocal = {
                LivePagedListBuilder(
                    repositoryDao.getRepositories(),
                    perPage
                ).build()
            }
        )

    fun deleteData(): Job = CoroutineScope(IO).launch {
        repositoryDao.deleteRepositories()
    }
}