package com.example.catalogue.di

import com.example.catalogue.component.detail.DetailModule
import com.example.catalogue.component.list.ListModule
import com.example.catalogue.component.main.HomeActivity
import com.example.catalogue.di.scope.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [ViewModelModule::class, ListModule::class, DetailModule::class])
    abstract fun contributeHomeActivity(): HomeActivity
}
