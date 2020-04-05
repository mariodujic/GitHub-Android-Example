package com.groundzero.github.feature.content.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.groundzero.github.feature.content.search.data.SearchRepository
import com.groundzero.github.feature.content.search.data.SortType
import javax.inject.Inject


class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {

    var isLoadingOnScroll = false
    var isToggleButtonShown = false
    var currentPage = 1
        private set
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
        repository.searchQuery(query, currentPage, ITEMS_PER_PAGE, getSortTypeLive().value!!)
    }

    fun setInitialQuery(query: String) {
        if (queryLive.value == null) {
            searchRepositories(query)
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
        sortTypeLive.value = nextSortType()
        queryLive.postValue(lastQueryValue())
    }

    private fun nextSortType(): SortType {
        val currentIndex = sortArray.indexOf(sortTypeLive.value!!)
        return sortArray[if (sortArray.size != currentIndex + 1) currentIndex + 1 else 0]
    }

    fun getQueryLive() = queryLive

    private fun lastQueryValue(): String? = queryLive.value
    fun getSortTypeLive(): LiveData<SortType> = sortTypeLive

    companion object {
        const val ITEMS_PER_PAGE = 20
    }
}