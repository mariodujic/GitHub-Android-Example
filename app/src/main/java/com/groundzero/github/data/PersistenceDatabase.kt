package com.groundzero.github.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.groundzero.github.feature.authentication.data.AccessToken
import com.groundzero.github.feature.authentication.data.AccessTokenDao
import com.groundzero.github.feature.content.owner.data.Owner
import com.groundzero.github.feature.content.owner.data.OwnerDao
import com.groundzero.github.feature.content.search.data.Repository
import com.groundzero.github.feature.content.search.data.RepositoryConverter
import com.groundzero.github.feature.content.search.data.RepositoryDao

@TypeConverters(RepositoryConverter::class)
@Database(
    entities = [Repository::class, Owner::class, AccessToken::class],
    exportSchema = false,
    version = 1
)
abstract class PersistenceDatabase : RoomDatabase() {

    abstract fun getOwnerDao(): OwnerDao
    abstract fun getRepositoryDao(): RepositoryDao
    abstract fun getAccessTokeNDao(): AccessTokenDao

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