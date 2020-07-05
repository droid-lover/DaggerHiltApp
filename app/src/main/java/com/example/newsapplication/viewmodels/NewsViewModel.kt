package com.example.newsapplication.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapplication.models.Articles
import com.example.newsapplication.repository.NewsRepo

/**
 * Created by Sachin
 */
class NewsViewModel(newsRepo: NewsRepo) : ViewModel() {

    lateinit var repo: NewsRepo

    init {
        this.repo = newsRepo
    }

    var showProgressBar = repo.showProgressBar

    var newsHeadlines = repo.newsHeadlines

    override fun onCleared() {
        super.onCleared()
        repo.onCleared()
    }

    fun getNewsHeadlines() {
        newsHeadlines = repo.getNewsHeadlinesData()
    }

}

