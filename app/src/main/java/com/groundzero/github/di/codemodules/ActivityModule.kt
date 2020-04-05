package com.groundzero.github.di.codemodules

import com.groundzero.github.feature.authentication.common.AuthenticationActivity
import com.groundzero.github.feature.content.common.MainActivity
import com.groundzero.github.feature.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [AuthenticationFragmentModule::class])
    abstract fun contributeAuthenticationActivity(): AuthenticationActivity
}