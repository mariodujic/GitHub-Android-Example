package com.groundzero.github.feature.search.data

import com.groundzero.github.data.BaseDataSource
import com.groundzero.github.feature.search.api.SearchApi
import javax.inject.Inject

class SearchDataSource @Inject constructor(private val api: SearchApi) : BaseDataSource() {
    suspend fun searchQuery(query: String, page: Int, perPage: Int, sortType: SortType) =
        getResult { api.searchQueryRepositories(query, page, perPage, sortType.getType()) }
}