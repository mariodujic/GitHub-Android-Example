package com.groundzero.github.feature.content.owner.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OwnerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOwner(owner: Owner)

    @Query("SELECT * from owners WHERE name = :username LIMIT 1")
    fun getOwner(username: String): LiveData<Owner>
}