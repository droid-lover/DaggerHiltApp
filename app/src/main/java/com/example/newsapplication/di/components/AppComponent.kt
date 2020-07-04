package com.example.newsapplication.di.components

import com.example.newsapplication.di.modules.ApiModule
import com.example.newsapplication.di.modules.AppModule
import com.example.newsapplication.repository.NewsRepo
import com.example.newsapplication.viewmodels.NewsViewModel
import com.example.newsapplication.viewmodels.NewsViewModelViewFactory
import com.example.newsapplication.views.fragments.NewsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Sachin
 */
@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {

    fun inject(newsRepo: NewsRepo)
    fun inject(newsViewModel: NewsViewModel)
    fun inject(retroViewModelFactory: NewsViewModelViewFactory)
    fun inject(newsFragment: NewsFragment)

}