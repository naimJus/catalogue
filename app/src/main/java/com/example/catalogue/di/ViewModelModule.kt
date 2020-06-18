package com.example.catalogue.di

import androidx.lifecycle.ViewModelProvider
import com.example.catalogue.util.ViewModelFactory
import dagger.Binds
import dagger.Module


@Module
@Suppress("UNUSED")
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
