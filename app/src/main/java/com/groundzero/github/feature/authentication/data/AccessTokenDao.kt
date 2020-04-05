package com.groundzero.github.feature.authentication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AccessTokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccessToken(accessToken: AccessToken)

    @Query("DELETE FROM access_token")
    suspend fun deleteAccessToken()

    @Query("SELECT * FROM access_token WHERE id=0 LIMIT 1")
    fun getAccessToken(): LiveData<AccessToken>
}