package com.example.catalogue.di

import com.example.catalogue.application.MainApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    interface Factory {
        fun create(module: AppModule): AppComponent
    }

}