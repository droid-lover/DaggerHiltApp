package com.example.newsapplication.views.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapplication.R
import com.example.newsapplication.models.Articles
import com.example.newsapplication.utils.C
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.content_news_detail.*
import java.text.SimpleDateFormat
/**
 * Created by Sachin
 */
class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        setSupportActionBar(toolbar)
        toolbar.contentInsetStartWithNavigation = 0
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getNewsDataFromIntent()
    }

    private fun getNewsDataFromIntent() {
        val newsArticle = intent?.getSerializableExtra(C.NEWS_DATA)
        newsArticle?.also {
            setUpNewsLayout(it as Articles)
        }
    }

    private fun setUpNewsLayout(article: Articles) {
        Log.d("ComingHere", "Inside_setUpNewsLayout ${Gson().toJson(article)}")
        article.urlToImage?.also { sdvNewsDetailsCover.setImageURI(it) }
        article.title?.also { tvNewsDetailsTitle.text = it }
        article.description?.also { tvNewsDetailsDescription.text = it }
        article.publishedAt?.also { tvNewsDetailsPublishedDate.text = getDayFromDate(it) }
        article.author?.also { tvNewsDetailsAuthor.text = "Author - $it\n" }
        article.content?.also { tvNewsDetailsOtherData.text =it}
    }

    private fun getDayFromDate(dateValue: String): String {
        val date = SimpleDateFormat("yyyy-MM-dd").parse(dateValue)
        return SimpleDateFormat("dd-MM-yyyy").format(date)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
