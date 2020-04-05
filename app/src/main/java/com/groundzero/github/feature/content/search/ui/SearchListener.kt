package com.groundzero.github.feature.content.search.ui

import com.groundzero.github.feature.content.owner.data.Owner
import com.groundzero.github.feature.content.search.data.Repository

interface SearchListener {
    fun onSearchRepositoryClick(repository: Repository)
    fun onSearchOwnerClick(owner: Owner)
}