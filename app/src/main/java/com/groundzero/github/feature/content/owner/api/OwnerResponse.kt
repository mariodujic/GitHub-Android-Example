package com.groundzero.github.feature.content.owner.api

import com.google.gson.annotations.SerializedName

data class OwnerResponse(
    val id: Long,
    @SerializedName("login")
    val name: String,
    @SerializedName("avatar_url")
    val avatar: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val type: String,
    @SerializedName("site_admin")
    val isSiteAdmin: Boolean,
    val company: String,
    val location: String,
    val email: String,
    val bio: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)