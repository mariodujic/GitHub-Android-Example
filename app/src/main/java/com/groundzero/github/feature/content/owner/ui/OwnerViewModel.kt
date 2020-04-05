package com.groundzero.github.feature.content.owner.ui

import androidx.lifecycle.ViewModel
import com.groundzero.github.feature.content.owner.data.OwnerRepository
import javax.inject.Inject

class OwnerViewModel @Inject constructor(private val repository: OwnerRepository) : ViewModel() {
    fun getOwner(username: String) = repository.getOwner(username)
}