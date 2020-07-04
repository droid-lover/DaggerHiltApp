package com.example.newsapplication.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.app.NewsApp
import com.example.newsapplication.di.components.AppComponent
import com.example.newsapplication.repository.NewsRepo
import javax.inject.Inject

/**
 * Created by Sachin
 */
class NewsViewModelViewFactory : ViewModelProvider.Factory {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var newsRepo: NewsRepo


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        appComponent = NewsApp.appComponent
        appComponent.inject(this)
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(newsRepo) as T
        }
        throw IllegalArgumentException("Something went wrong unknown argument")
    }


}

