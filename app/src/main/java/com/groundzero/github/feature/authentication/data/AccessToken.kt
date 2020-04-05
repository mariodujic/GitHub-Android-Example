package com.groundzero.github.feature.authentication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "access_token")
data class AccessToken(
    val token: String? = null,
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0
)