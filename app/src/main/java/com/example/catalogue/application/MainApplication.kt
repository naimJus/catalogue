package com.example.catalogue.application

import com.example.catalogue.di.AppModule
import com.example.catalogue.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(AppModule(this))
    }
}