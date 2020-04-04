package com.groundzero.github.feature.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.groundzero.github.feature.search.data.SearchRepository
import com.groundzero.github.feature.search.data.SortType
import javax.inject.Inject


class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {

    var isLoadingOnScroll = false
    var isToggleButtonShown: Boolean = false
    private var currentPage = 1
    private val queryLive = MutableLiveData<String>()
    private var sortArray = mutableListOf(
        SortType.STARS,
        SortType.FORKS,
        SortType.UPDATES
    )

    private val sortTypeLive = MutableLiveData<SortType>().apply {
        this.value = sortArray[0]
    }

    val repositoryLive = Transformations.switchMap(queryLive) { query ->
        repository.searchQuery(query, currentPage, 20, sortTypeLive.value!!)
    }

    fun setInitialQuery() {
        if (queryLive.value == null) {
            searchRepositories("Android")
        }
    }

    fun searchRepositories(queryString: String?) {
        if (queryString != null) {
            repository.deleteData()
            queryLive.postValue(queryString)
        }
    }

    fun nextPage() {
        currentPage++
        queryLive.postValue(lastQueryValue())
    }

    fun nextSort() {
        repository.deleteData()
        val currentIndex = sortArray.indexOf(sortTypeLive.value!!)
        val nextSortType =
            sortArray[if (sortArray.size != currentIndex + 1) currentIndex + 1 else 0]
        sortTypeLive.value = nextSortType
        queryLive.postValue(lastQueryValue())
    }

    private fun lastQueryValue(): String? = queryLive.value
    fun getSortTypeLive(): LiveData<SortType> = sortTypeLive
}