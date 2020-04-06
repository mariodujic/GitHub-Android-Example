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

    @Query("SELECT * FROM access_token")
    fun getAccessToken(): LiveData<AccessToken>

    @Query("SELECT * FROM access_token")
    fun getAccessTokenSync(): AccessToken?
}