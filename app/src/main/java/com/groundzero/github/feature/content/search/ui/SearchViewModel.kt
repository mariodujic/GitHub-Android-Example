package com.groundzero.github.feature.content.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.groundzero.github.feature.content.search.data.SearchRepository
import com.groundzero.github.feature.content.search.data.SortType
import com.groundzero.github.feature.content.user.data.UserRepository
import javax.inject.Inject


class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    var isLoadingOnScroll = false
    var isToggleButtonShown = false
    var currentPage = INITIAL_PAGE
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
        searchRepository.searchQuery(query, currentPage, ITEMS_PER_PAGE, getSortTypeLive().value!!)
    }

    fun setInitialQuery(query: String) {
        if (queryLive.value == null) {
            searchRepositories(query)
        }
    }

    fun searchRepositories(queryString: String?) {
        if (queryString != null) {
            searchRepository.deleteData()
            queryLive.postValue(queryString)
        }
    }

    fun nextPage() {
        currentPage++
        queryLive.postValue(lastQueryValue())
    }

    fun nextSort() {
        setToInitialPage()
        searchRepository.deleteData()
        sortTypeLive.value = nextSortType()
        queryLive.postValue(lastQueryValue())
    }

    private fun setToInitialPage() {
        currentPage = INITIAL_PAGE
    }

    private fun nextSortType(): SortType {
        val currentIndex = sortArray.indexOf(sortTypeLive.value!!)
        return sortArray[if (sortArray.size != currentIndex + 1) currentIndex + 1 else 0]
    }

    fun getQueryLive() = queryLive

    private fun lastQueryValue(): String? = queryLive.value
    fun getSortTypeLive(): LiveData<SortType> = sortTypeLive

    fun deleteUserData() {
        userRepository.deleteAccessToken()
        userRepository.deleteUser()
    }

    fun getUserLive() = userRepository.getUser()

    companion object {
        const val ITEMS_PER_PAGE = 20
        const val INITIAL_PAGE = 1
    }
}