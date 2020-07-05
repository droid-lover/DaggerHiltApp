package com.example.newsapplication.viewmodels

import androidx.lifecycle.ViewModel
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
    var error = repo.error

    override fun onCleared() {
        super.onCleared()
        repo.onCleared()
    }

    fun getNewsHeadlines() {
        newsHeadlines = repo.getNewsHeadlinesData()
    }

}

