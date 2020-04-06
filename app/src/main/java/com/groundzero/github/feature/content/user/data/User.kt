package com.groundzero.github.feature.content.user.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val login: String? = null,
    val avatar: String? = null,
    val htmlUrl: String? = null,
    val name: String? = null,
    val company: String? = null,
    val location: String? = null,
    val email: String? = null,
    val bio: String? = null,
    val publicRepoCount: Long? = null,
    val followers: Long? = null,
    val following: Long? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
): Serializable