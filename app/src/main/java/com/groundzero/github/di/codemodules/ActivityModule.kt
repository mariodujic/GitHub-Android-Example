package com.groundzero.github.di.codemodules

import com.groundzero.github.feature.authentication.common.AuthenticationActivity
import com.groundzero.github.feature.content.common.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [AuthenticationFragmentModule::class])
    abstract fun contributeAuthenticationActivity(): AuthenticationActivity
}