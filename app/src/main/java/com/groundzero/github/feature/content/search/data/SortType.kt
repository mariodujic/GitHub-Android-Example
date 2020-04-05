package com.groundzero.github.feature.content.search.data

enum class SortType(private val sortType: String) {
    STARS("stars"),
    FORKS("forks"),
    UPDATES("updated");

    fun getType() = sortType
}