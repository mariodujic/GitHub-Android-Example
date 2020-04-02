package com.groundzero.github.di.codemodules

import com.groundzero.github.di.modules.PersistenceModule
import com.groundzero.github.di.modules.RemoteModule
import com.groundzero.github.di.modules.ViewModelModule
import dagger.Module

@Module(
    includes = [
        PersistenceModule::class,
        RemoteModule::class,
        ViewModelModule::class
    ]
)
interface AppModule