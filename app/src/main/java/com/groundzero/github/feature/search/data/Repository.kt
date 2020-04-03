package com.groundzero.github.feature.search.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.groundzero.github.feature.owner.data.Owner
import java.io.Serializable

@Entity(tableName = "repository")
data class Repository(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String? = null,
    @SerializedName("full_name")
    val fullName: String? = null,
    @SerializedName("html_url")
    val htmlUrl: String? = null,
    val description: String? = null,
    @SerializedName("fork")
    val isFork: Boolean? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @TypeConverters(RepositoryConverter::class)
    val owner: Owner? = null,
    val watchers: Long? = null,
    val forks: Long? = null,
    @SerializedName("open_issues")
    val issues: Long? = null
) : Serializable