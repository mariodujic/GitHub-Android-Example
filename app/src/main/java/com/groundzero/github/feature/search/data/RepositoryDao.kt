package com.groundzero.github.feature.search.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repositories: List<Repository>)

    @Query("SELECT * FROM repository")
    fun getRepositories(): LiveData<List<Repository>>

    @Query("DELETE FROM repository")
    suspend fun deleteRepositories()

    // Do a similar query as the search API:
    // Look for repos that contain the query string in the name or in the description
    // and order those results descending, by the number of stars and then by name
    @Query("SELECT * FROM repository")
    fun reposByName(): androidx.paging.DataSource.Factory<Int, Repository>

}