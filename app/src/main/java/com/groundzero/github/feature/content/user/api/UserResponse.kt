package com.groundzero.github.feature.content.user.api

import com.google.gson.annotations.SerializedName

data class UserResponse (
    val id: Long,
    val login: String,
    @SerializedName("avatar_url")
    val avatar: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val name: String,
    val company: String,
    val location: String,
    val email: String,
    val bio: String,
    @SerializedName("public_repos")
    val publicRepoCount: Long,
    val followers: Long,
    val following: Long,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)