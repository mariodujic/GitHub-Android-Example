package com.groundzero.github.di.codemodules

import com.groundzero.github.base.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}