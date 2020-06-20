package com.example.catalogue.component.detail

import androidx.lifecycle.ViewModel
import com.example.catalogue.di.ViewModelKey
import com.example.catalogue.di.scope.PerFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindListViewModel(viewModel: DetailsViewModel): ViewModel


    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun contributeDetailFragment(): DetailFragment
}