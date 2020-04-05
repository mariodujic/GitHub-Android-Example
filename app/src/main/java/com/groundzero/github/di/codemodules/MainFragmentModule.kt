package com.groundzero.github.di.codemodules

import com.groundzero.github.feature.content.owner.ui.OwnerFragment
import com.groundzero.github.feature.content.repository.RepositoryFragment
import com.groundzero.github.feature.content.search.ui.SearchFragment
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