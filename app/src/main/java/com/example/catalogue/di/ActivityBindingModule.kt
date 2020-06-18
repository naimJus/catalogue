package com.example.catalogue.di

import com.example.catalogue.component.main.HomeActivity
import com.example.catalogue.di.scope.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeHomeActivity(): HomeActivity
}
