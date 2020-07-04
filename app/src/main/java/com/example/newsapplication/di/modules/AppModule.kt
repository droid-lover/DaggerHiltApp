package com.example.newsapplication.di.modules

import com.example.newsapplication.app.NewsApp
import dagger.Module
import dagger.Provides
/**
 * Created by Sachin
 */
@Module
class AppModule constructor(newsApp: NewsApp) {

    var newsApp: NewsApp

    init {
        this.newsApp = newsApp
    }

    @Provides
    fun providesAppReference(): NewsApp {
        return newsApp
    }
}