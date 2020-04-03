package com.groundzero.github.feature.search.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePage(page: Page)

    @Query("SELECT * FROM page")
    fun getPage(): Page

    @Query("DELETE FROM page")
    suspend fun deletePage()
}