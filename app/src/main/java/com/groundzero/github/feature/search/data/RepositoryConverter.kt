package com.groundzero.github.feature.search.data

import androidx.room.TypeConverter
import com.google.gson.Gson

class RepositoryConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromOwnerToString(owner: RepositoryOwner): String = gson.toJson(owner)

    @TypeConverter
    fun fromStringToOwner(ownerS: String): RepositoryOwner =
        gson.fromJson(ownerS, RepositoryOwner::class.java)
}