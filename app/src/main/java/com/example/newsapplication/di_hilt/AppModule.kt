package com.example.newsapplication.di_hilt

import com.example.newsapplication.app.NewsApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun providesContext()= NewsApp.applicationContext()
}