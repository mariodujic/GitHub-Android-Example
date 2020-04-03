package com.groundzero.github.feature.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.groundzero.github.data.Result
import com.groundzero.github.feature.search.data.Repository
import com.groundzero.github.feature.search.data.SearchRepository
import javax.inject.Inject


class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {

    var loadingData: Boolean = false
    private var currentPage: Int = -1
    private var query: String = "Android"

    fun searchRepository(
        restartList: Boolean
    ): LiveData<Result<List<Repository>>> {
        if (restartList) {
            resetPageCount()
        } else {
            incrementPage()
        }
        println("Current page: $currentPage")
        println("Current query: $query")
        return repository.searchQueryWithDelete(query, currentPage, restartList)
    }

    private fun incrementPage() = apply { currentPage++ }
    private fun resetPageCount() = apply { currentPage = 0 }
    fun setQuery(query: String) = apply { this.query = query }
}