package com.groundzero.github.di.modules

import android.app.Application
import com.groundzero.github.data.PersistenceDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun provideRepositoryDao(persistenceDatabase: PersistenceDatabase) =
        persistenceDatabase.getRepositoryDao()

    @Provides
    @Singleton
    fun providePersistenceDatabase(app: Application) = PersistenceDatabase.getInstance(app)
}