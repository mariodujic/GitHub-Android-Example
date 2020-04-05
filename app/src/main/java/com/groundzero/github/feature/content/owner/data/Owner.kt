package com.groundzero.github.feature.content.owner.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "owners")
data class Owner(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    @SerializedName("login")
    val name: String? = null,
    @SerializedName("avatar_url")
    val avatar: String? = null,
    @SerializedName("html_url")
    val htmlUrl: String? = null,
    val type: String? = null,
    @SerializedName("site_admin")
    val isSiteAdmin: Boolean? = null,
    val company: String? = null,
    val location: String? = null,
    val email: String? = null,
    val bio: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null
) : Serializable