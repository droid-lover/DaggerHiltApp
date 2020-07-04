package com.example.newsapplication.viewmodels

import android.content.Context
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

    val newsHeadlines = repo.newsHeadlines

    override fun onCleared() {
        super.onCleared()
        repo.onCleared()
    }

    fun getNewsHeadlines(context: Context) = repo.getNewsHeadlines(context)

}

