package com.groundzero.github.di.codemodules

import com.groundzero.github.feature.owner.ui.OwnerFragment
import com.groundzero.github.feature.repository.RepositoryFragment
import com.groundzero.github.feature.search.ui.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributeRepositoryFragment(): RepositoryFragment

    @ContributesAndroidInjector
    abstract fun contributeOwnerFragment(): OwnerFragment
}