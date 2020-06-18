package com.example.catalogue.di

import android.app.Application
import dagger.Module
import dagger.Provides

/**
 * Define here all objects that are shared throughout the app, like SharedPreferences, navigators or
 * others.
 */
@Module
class AppModule(private val application: Application) {

    @Provides
    fun provideApplication(): Application {
        return application
    }
}