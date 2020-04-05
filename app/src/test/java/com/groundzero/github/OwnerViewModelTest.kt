package com.groundzero.github

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.groundzero.github.feature.owner.data.OwnerRepository
import com.groundzero.github.feature.owner.ui.OwnerViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class OwnerViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: OwnerRepository
    private lateinit var viewModel: OwnerViewModel

    @Before
    fun onSetup() {
        viewModel = OwnerViewModel(repository)
    }

    @Test
    fun `when fetching repository owner data call repository function`() {
        viewModel.getOwner(USERNAME)
        Mockito.verify(repository, times(1)).getOwner(USERNAME)
    }

    companion object {
        const val USERNAME = "John"
    }
}