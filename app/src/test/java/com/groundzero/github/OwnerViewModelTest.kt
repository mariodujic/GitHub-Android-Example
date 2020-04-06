package com.groundzero.github

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.groundzero.github.data.Result
import com.groundzero.github.feature.content.owner.data.Owner
import com.groundzero.github.feature.content.owner.data.OwnerRepository
import com.groundzero.github.feature.content.owner.ui.OwnerViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
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

    @Test
    fun `live data returning correct value`() {
        val ownerLiveData = MutableLiveData<Result<Owner>>()
        ownerLiveData.value = result
        `when`(repository.getOwner(USERNAME)).thenReturn(ownerLiveData)
        assertEquals(viewModel.getOwner(USERNAME).value, result)
    }

    companion object {
        const val USERNAME = "John"
        private val result = Result.success(Owner(0))
    }
}