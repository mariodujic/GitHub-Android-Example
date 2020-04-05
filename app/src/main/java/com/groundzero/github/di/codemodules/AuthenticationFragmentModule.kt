package com.groundzero.github.di.codemodules

import com.groundzero.github.feature.authentication.AuthenticateFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthenticationFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeAuthenticate(): AuthenticateFragment
}