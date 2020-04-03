package com.groundzero.github.feature.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.groundzero.github.data.Result
import com.groundzero.github.feature.search.data.Repository
import com.groundzero.github.feature.search.data.SearchRepository
import javax.inject.Inject


class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {
    fun searchRepository(query: String, page: Int): LiveData<Result<List<Repository>>> =
        repository.searchQuery(query, page)
}