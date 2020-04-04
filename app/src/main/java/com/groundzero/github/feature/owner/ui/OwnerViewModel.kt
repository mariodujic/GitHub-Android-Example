package com.groundzero.github.feature.owner.ui

import androidx.lifecycle.ViewModel
import com.groundzero.github.feature.owner.data.OwnerRepository
import javax.inject.Inject

class OwnerViewModel @Inject constructor(private val repository: OwnerRepository) : ViewModel() {

    fun getOwner(username: String) = repository.getOwner(username)
}