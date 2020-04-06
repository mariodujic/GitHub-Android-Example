package com.groundzero.github

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.groundzero.github.feature.authentication.data.AccessToken
import com.groundzero.github.feature.authentication.data.AccessTokenDao
import com.groundzero.github.feature.splash.SplashViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var dao: AccessTokenDao
    private lateinit var viewModel: SplashViewModel

    @Before
    fun onSetup() {
        viewModel = SplashViewModel(dao)
    }

    @Test
    fun `retrieving access token live data`() {
        viewModel.accessTokenLive()
        verify(dao, times(1)).getAccessToken()
    }

    @Test
    fun `access token live data returns correct value`() {
        val accessTokenLive = MutableLiveData<AccessToken>()
        accessTokenLive.value = TOKEN
        `when`(dao.getAccessToken()).thenReturn(accessTokenLive)
        viewModel.accessTokenLive().observeForever {}
        viewModel.accessTokenLive().getOrAwaitValue(2, TimeUnit.SECONDS)
        assertEquals(viewModel.accessTokenLive().value, TOKEN)
    }

    companion object {
        private val TOKEN = AccessToken("0", 0)
    }
}