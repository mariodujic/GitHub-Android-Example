package com.groundzero.github.feature.search.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("page")
data class Page(
    val pageCount: Int,
    val pageQuery: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0
)