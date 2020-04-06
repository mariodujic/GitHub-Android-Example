package com.groundzero.github

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.groundzero.github.feature.content.search.data.SearchRepository
import com.groundzero.github.feature.content.search.data.SortType
import com.groundzero.github.feature.content.search.ui.SearchViewModel
import com.groundzero.github.feature.content.search.ui.SearchViewModel.Companion.ITEMS_PER_PAGE
import com.groundzero.github.feature.content.user.data.UserRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var searchRepository: SearchRepository

    @Mock
    lateinit var userRepository: UserRepository
    private lateinit var viewModel: SearchViewModel

    @Before
    fun onSetup() {
        viewModel = SearchViewModel(searchRepository, userRepository)
    }

    @Test
    fun `initial required configuration`() {
        assertEquals(viewModel.isLoadingOnScroll, false)
        assertEquals(viewModel.isToggleButtonShown, false)
        assertEquals(viewModel.currentPage, 1)
        assertEquals(viewModel.getSortTypeLive().value, SortType.STARS)
    }

    @Test
    fun `setting initial live data query`() {
        viewModel.repositoryLive.observeForever {}
        viewModel.setInitialQuery(QUERY)
        assertEquals(viewModel.getQueryLive().getOrAwaitValue(2), "Android")
    }

    @Test
    fun `searching new query deletes previous data from database`() {
        viewModel.searchRepositories(QUERY)
        verify(searchRepository, times(1)).deleteData()
    }

    @Test
    fun `searching null query does not delete previous data`() {
        viewModel.searchRepositories(null)
        verify(searchRepository, times(0)).deleteData()
    }

    @Test
    fun `calling next page increments currentPage`() {
        viewModel.nextPage()
        assertEquals(viewModel.currentPage, 2)
    }

    @Test
    fun `changing sort while searching repositories deletes previous data`() {
        viewModel.nextSort()
        verify(searchRepository, times(1)).deleteData()
    }

    @Test
    fun `iterating all sort types in correct order`() {
        assertEquals(viewModel.getSortTypeLive().value, SortType.STARS)
        viewModel.nextSort()
        assertEquals(viewModel.getSortTypeLive().value, SortType.FORKS)
        viewModel.nextSort()
        assertEquals(viewModel.getSortTypeLive().value, SortType.UPDATES)
        viewModel.nextSort()
        assertEquals(viewModel.getSortTypeLive().value, SortType.STARS)
    }

    @Test
    fun `repository should send request on query update`() {
        viewModel.setInitialQuery(QUERY)
        viewModel.repositoryLive.observeForever {}
        verify(searchRepository, times(1))
            .searchQuery(
                QUERY,
                viewModel.currentPage,
                ITEMS_PER_PAGE,
                viewModel.getSortTypeLive().value!!
            )
    }

    @Test
    fun `deleting user data should remove token and user data`() {
        viewModel.deleteUserData()
        verify(userRepository, times(1)).deleteAccessToken()
        verify(userRepository, times(1)).deleteUser()
    }

    @Test
    fun `repository returns user data`() {
        viewModel.getUserLive()
        verify(userRepository, times(1)).getUser()
    }

    companion object {
        private const val QUERY = "Android"
    }
}