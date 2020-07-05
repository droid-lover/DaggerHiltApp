package com.example.newsapplication.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapplication.apis.APIs
import com.example.newsapplication.app.NewsApp
import com.example.newsapplication.database.NewsDatabase
import com.example.newsapplication.di.components.AppComponent
import com.example.newsapplication.models.Articles
import com.example.newsapplication.utils.Result
import com.example.newsapplication.utils.C
import com.google.gson.Gson
import com.example.newsapplication.models.NewsHeadlines
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.security.auth.callback.Callback

/**
 * Created by Sachin.
 */
class NewsRepo : Repo() {

    init {
        val appComponent = NewsApp.appComponent
        appComponent.inject(this)
    }

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var context: Context

    private val _newsHeadlines: MutableLiveData<List<Articles>> = MutableLiveData()
    var newsHeadlines: LiveData<List<Articles>> = _newsHeadlines

    private val _error: MutableLiveData<String> = MutableLiveData()
    var error: LiveData<String> = _error


    fun getNewsHeadlinesData(): LiveData<List<Articles>> {
        _showProgressBar.value = true

        disposables.add(
            Maybe.fromCallable {
                NewsDatabase.getDatabase(context).newsDao().getNewsHeadlinesFromDB()
            }.toSingle().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<NewsHeadlines>() {
                    override fun onSuccess(newsHeadlines: NewsHeadlines) {
//                        Log.d("ComingHere", "inside_onSuccess ${Gson().toJson(newsHeadlines)}")
                        _newsHeadlines.value = newsHeadlines.articles
                    }

                    override fun onError(e: Throwable) {
//                        Log.d("ComingHere", "inside_onError ${e.localizedMessage}")
                        getNewsHeadlinesFromServer()
                    }

                })
        )
        return _newsHeadlines
    }

    fun getNewsHeadlinesFromServer(): LiveData<List<Articles>> {
        val apis: APIs = retrofit.create(APIs::class.java)

        disposables.add(
            apis.getNewsHeadlines("us", C.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableSingleObserver<NewsHeadlines>() {
                    override fun onSuccess(data: NewsHeadlines) {
                        _newsHeadlines.postValue(data.articles)
                        saveNewsInDb(context, data)
                        _showProgressBar.postValue(false)
                    }

                    override fun onError(throwable: Throwable) {
                        _error.postValue("Error: ${throwable.localizedMessage}")
                        _showProgressBar.postValue(false)
                    }
                })
        )
        return _newsHeadlines

    }

    private fun saveNewsInDb(context: Context, data: NewsHeadlines) {
        val news = NewsHeadlines(data.status, data.totalResults, data.articles)
        Observable.fromCallable {
            NewsDatabase.getDatabase(context).newsDao()
                .insertNews(news)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }


}
