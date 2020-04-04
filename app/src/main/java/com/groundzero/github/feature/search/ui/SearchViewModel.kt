package com.groundzero.github.feature.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.groundzero.github.feature.search.data.SearchRepository
import com.groundzero.github.feature.search.data.SearchSort
import javax.inject.Inject


class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {

    var loadingData = false
    var toggleButtonShown: Boolean = false
    private val queryLiveData = MutableLiveData<String>()
    private var page = 1
    private var sortArray = mutableListOf(
        SearchSort.STARS,
        SearchSort.FORKS,
        SearchSort.UPDATES
    )
    private val sortTypeLive = MutableLiveData<SearchSort>().apply {
        this.value = sortArray[0]
    }

    val repoResult = Transformations.switchMap(queryLiveData) { query ->
        repository.searchQuery(query ?: "Android", page, 20, sortTypeLive.value!!)
    }

    fun searchRepo(queryString: String?) {
        if (queryString != null) {
            repository.deleteData()
            queryLiveData.postValue(queryString)
        }
    }

    fun nextPage() {
        page++
        queryLiveData.postValue(lastQueryValue())
    }

    fun nextSort() {
        repository.deleteData()
        val currentIndex = sortArray.indexOf(sortTypeLive.value!!)
        val nextSortType = sortArray[if(sortArray.size != currentIndex+1) currentIndex + 1 else 0]
        sortTypeLive.value = nextSortType
        queryLiveData.postValue(lastQueryValue())
    }

    private fun lastQueryValue(): String? = queryLiveData.value
    fun getSortTypeLive(): LiveData<SearchSort> = sortTypeLive
}