package com.groundzero.github.di.components

import android.app.Application
import com.groundzero.github.base.App
import com.groundzero.github.di.codemodules.ActivityModule
import com.groundzero.github.di.codemodules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class
    ]
)
interface AppComponent {
    fun inject(application: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}