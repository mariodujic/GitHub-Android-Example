package com.groundzero.github.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.groundzero.github.di.helper.ViewModelFactory
import com.groundzero.github.di.scopes.ViewModelKey
import com.groundzero.github.feature.owner.ui.OwnerViewModel
import com.groundzero.github.feature.search.ui.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(OwnerViewModel::class)
    abstract fun bindOwnerViewModel(viewModel: OwnerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}