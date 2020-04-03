package com.groundzero.github.feature.search.ui

import com.groundzero.github.feature.search.data.Repository

interface SearchListener {
    fun onSearchItemClick(repository: Repository)
}