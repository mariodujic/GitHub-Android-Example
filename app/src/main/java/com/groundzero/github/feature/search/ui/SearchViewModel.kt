package com.groundzero.github.feature.search.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.groundzero.github.feature.search.data.SearchRepository
import javax.inject.Inject


class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {

    var loadingData = false
    private val queryLiveData = MutableLiveData<String>()
    private val pageLiveData = MutableLiveData<Int>()
    private var page = 0
    val repoResult = Transformations.switchMap(queryLiveData) { query ->
        repository.searchQuery(query, page, 20)
    }

    fun searchRepo(queryString: String) {
        repository.deleteData()
        queryLiveData.postValue(queryString)
    }

    fun nextPage() {
        pageLiveData.postValue(++page)
        queryLiveData.postValue(lastQueryValue())
    }

    private fun lastQueryValue(): String? = queryLiveData.value
}