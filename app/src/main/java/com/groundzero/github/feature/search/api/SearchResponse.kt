package com.groundzero.github.feature.search.api

import com.google.gson.annotations.SerializedName
import com.groundzero.github.feature.search.data.Repository

data class SearchResponse(
    @SerializedName("total_count")
    val totalCount: Int = 0,
    @SerializedName("items")
    val repositories: List<Repository> = emptyList(),
    val nextPage: Int? = null
)