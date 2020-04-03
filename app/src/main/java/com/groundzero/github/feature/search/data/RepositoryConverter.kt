package com.groundzero.github.feature.search.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.groundzero.github.feature.owner.data.Owner

class RepositoryConverter {

    val gson = Gson()

    @TypeConverter
    fun fromOwnerToString(owner: Owner) = gson.toJson(owner)

    @TypeConverter
    fun fromStringToOwner(ownerS: String) = gson.fromJson(ownerS, Owner::class.java)
}