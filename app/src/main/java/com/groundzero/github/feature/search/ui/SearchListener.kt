package com.groundzero.github.feature.search.ui

import com.groundzero.github.feature.search.data.Repository
import com.groundzero.github.feature.search.data.RepositoryOwner

interface SearchListener {
    fun onSearchRepositoryClick(repository: Repository)
    fun onSearchOwnerClick(repositoryOwner: RepositoryOwner)
}