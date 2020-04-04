package com.groundzero.github.feature.search.data

enum class SearchSort(private val sortType: String) {
    STARS("stars"),
    FORKS("forks"),
    UPDATES("updated");

    fun getType() = sortType
}