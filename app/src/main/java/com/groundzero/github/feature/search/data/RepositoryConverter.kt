package com.groundzero.github.feature.search.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.groundzero.github.feature.owner.data.Owner

class RepositoryConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromOwnerToString(owner: Owner): String = gson.toJson(owner)

    @TypeConverter
    fun fromStringToOwner(ownerS: String): Owner = gson.fromJson(ownerS, Owner::class.java)
}