package com.groundzero.github.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.groundzero.github.feature.search.data.Repository
import com.groundzero.github.feature.search.data.RepositoryConverter
import com.groundzero.github.feature.search.data.RepositoryDao

@TypeConverters(RepositoryConverter::class)
@Database(entities = [Repository::class], exportSchema = false, version = 1)
abstract class PersistenceDatabase : RoomDatabase() {

    abstract fun getRepositoryDao(): RepositoryDao

    companion object {

        @Volatile
        private var instance: PersistenceDatabase? = null

        fun getInstance(
            context: Context
        ): PersistenceDatabase =
            instance
                ?: buildDatabase(
                    context
                ).also { instance = it }

        private fun buildDatabase(
            context: Context
        ): PersistenceDatabase {

            return Room.databaseBuilder(
                context, PersistenceDatabase::class.java,
                DATABASE_NAME
            )
                .build()
        }

        private const val DATABASE_NAME = "my_database"

    }
}