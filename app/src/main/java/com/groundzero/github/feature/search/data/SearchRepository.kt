package com.groundzero.github.feature.search.data

import com.groundzero.github.common.resultLiveDataRemote
import javax.inject.Inject

class SearchRepository @Inject constructor(private val dataSource: SearchDataSource) {
    fun searchQuery(query: String, page: Int) =
        resultLiveDataRemote { dataSource.searchQuery(query, page) }
}