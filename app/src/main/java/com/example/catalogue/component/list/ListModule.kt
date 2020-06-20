package com.example.catalogue.component.list

import androidx.lifecycle.ViewModel
import com.example.catalogue.di.ViewModelKey
import com.example.catalogue.di.scope.PerFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ListModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(viewModel: ListViewModel): ViewModel


    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun contributeListFragment(): ListFragment
}