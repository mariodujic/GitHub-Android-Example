package com.groundzero.github.feature.content.search.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repositories: List<Repository>)

    @Query("DELETE FROM repository")
    suspend fun deleteRepositories()

    @Query("SELECT * FROM repository")
    fun getRepositories(): androidx.paging.DataSource.Factory<Int, Repository>
}