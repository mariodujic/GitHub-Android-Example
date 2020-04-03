package com.groundzero.github.feature.search.ui

import androidx.lifecycle.ViewModel
import com.groundzero.github.feature.search.data.SearchRepository
import javax.inject.Inject


class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {
    fun searchRepository(query: String, page: Int) = repository.searchQuery(query, page)
}