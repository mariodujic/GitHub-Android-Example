package com.groundzero.github.feature.search.api

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val id: Long,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val description: String,
    @SerializedName("fork")
    val isFork: Boolean,
    @SerializedName("created_at")
    val createdAt: String
)