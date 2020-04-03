package com.groundzero.github.feature.search.data

import com.google.gson.annotations.SerializedName
import com.groundzero.github.feature.owner.data.Owner
import java.io.Serializable

data class Repository(
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
    val createdAt: String,
    val owner: Owner,
    val watchers: Long,
    val forks: Long,
    @SerializedName("open_issues")
    val issues: Long
) : Serializable