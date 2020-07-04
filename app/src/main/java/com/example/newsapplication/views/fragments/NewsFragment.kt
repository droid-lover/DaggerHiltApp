package com.example.newsapplication.views.fragments

import com.example.newsapplication.utils.Result
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.R
import com.example.newsapplication.adapter.NewsHeadlinesAdapter
import com.example.newsapplication.app.NewsApp
import com.example.newsapplication.di.components.AppComponent
import com.example.newsapplication.models.NewsHeadlines
import com.example.newsapplication.viewmodels.NewsViewModel
import com.example.newsapplication.viewmodels.NewsViewModelViewFactory
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class NewsFragment : Fragment(R.layout.fragment_news) {

    @Inject
    lateinit var newsViewModelViewFactory: NewsViewModelViewFactory

    private val newsViewModel by lazy { ViewModelProviders.of(this, newsViewModelViewFactory).get(NewsViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         NewsApp.appComponent.inject(this)
        observeViewModelChanges()
        newsViewModel.getNewsHeadlines(activity!!)
    }

    private fun observeViewModelChanges() {
        newsViewModel.newsHeadlines.observe(this, androidx.lifecycle.Observer {
            when (it) {
                is Result.Success<NewsHeadlines> -> {
                    //  Log.d("ComingHere", "InsideApiSuccess ${Gson().toJson(it.data)}")
                    setNews(it.data)
                }
                is Result.Failure -> {
                    //Log.d("ComingHere", "InsideApiFailure ${it.throwable.localizedMessage}")
                    activity?.also { activity ->
                        Toast.makeText(
                                activity,
                                it.throwable.localizedMessage,
                                Toast.LENGTH_LONG
                        )
                    }
                }
            }
        })
    }

    private fun setNews(data: NewsHeadlines) {
        with(shimmerViewContainer) {
            stopShimmerAnimation()
            visibility = View.GONE
        }

        activity?.also {

            rvNewsHeadlines.apply {
                visibility = View.VISIBLE
                adapter = NewsHeadlinesAdapter(it, data, true)
                layoutManager = LinearLayoutManager(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        shimmerViewContainer.startShimmerAnimation()
    }

    override fun onPause() {
        shimmerViewContainer.stopShimmerAnimation()
        super.onPause()
    }
}