package com.groundzero.github.feature.search.api

import com.google.gson.annotations.SerializedName
import com.groundzero.github.feature.search.data.Repo

data class SearchResponse(
    @SerializedName("total_count")
    val totalCount: Long,
    @SerializedName("items")
    val repositories: List<Repo>
)