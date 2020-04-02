package com.groundzero.github.feature.search.data

import com.groundzero.github.common.BaseDataSource
import com.groundzero.github.common.Result
import com.groundzero.github.feature.search.api.SearchApi
import com.groundzero.github.feature.search.api.SearchResponse
import javax.inject.Inject

class SearchDataSource @Inject constructor(private val api: SearchApi) : BaseDataSource() {
    suspend fun searchQuery(query: String) =
        getResult { api.searchQueryRepositories(query, 1) }
}