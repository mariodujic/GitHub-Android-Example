package com.groundzero.github.feature.search.ui

import androidx.lifecycle.ViewModel
import com.groundzero.github.feature.search.data.SearchRepository
import javax.inject.Inject


class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {

    var loadingData: Boolean = false
    private var currentPage: Int = -1
    private var query: String = "Android"
    val repositoriesLive = repository.searchQuery(query, currentPage)

    fun updateRepository() {
        incrementPage()
        repository.updateQuery(query, currentPage)
    }

    fun newRepositories(query: String) {
        setQuery(query)
        resetPageCount()
        repository.deleteData()
        repository.updateQuery(query, currentPage)
    }

    private fun incrementPage() = apply { currentPage++ }
    private fun resetPageCount() = apply { currentPage = 0 }
    private fun setQuery(query: String) = apply { this.query = query }
}