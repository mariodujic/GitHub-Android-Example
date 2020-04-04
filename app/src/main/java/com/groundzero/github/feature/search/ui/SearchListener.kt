package com.groundzero.github.feature.search.ui

import com.groundzero.github.feature.owner.data.Owner
import com.groundzero.github.feature.search.data.Repository

interface SearchListener {
    fun onSearchRepositoryClick(repository: Repository)
    fun onSearchOwnerClick(owner: Owner)
}