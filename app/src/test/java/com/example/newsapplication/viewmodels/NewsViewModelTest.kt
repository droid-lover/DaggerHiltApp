//package com.example.newsapplication.viewmodels
//
//import android.content.Context
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.MutableLiveData
//import com.example.newsapplication.app.NewsApp
//import com.example.newsapplication.models.Articles
//import com.example.newsapplication.repository.NewsRepo
//import org.junit.Assert
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.mockito.MockitoAnnotations
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class NewsViewModelTest {
//
//    lateinit var newsViewModel: NewsViewModel
//    @Mock
//    lateinit var newsRepo: NewsRepo
//
//    private lateinit var newsList:List<Articles>
//    private val mockNewsList:MutableList<Articles>  = mutableListOf()
//    private val mockNewsLiveData: MutableLiveData<List<Articles>> = MutableLiveData<List<Articles>>()
//
//    @get:Rule
//    val rule = InstantTaskExecutorRule()
//
//
//    @Before
//    fun init(){
//        MockitoAnnotations.initMocks(this);
//        this.newsViewModel = NewsViewModel(this.newsRepo)
//        mockData()
//        // retroViewModel.postInfoLiveData
//    }
//
//
//    @Test
//    fun fetchPostInfoFromRepositoryTest(){
//        Mockito.`when`(newsRepo.getNewsHeadlines(context = NewsApp.instance!!)).thenReturn(mockNewsLiveData)
//        newsViewModel.newsHeadlines = mockNewsLiveData
//        Assert.assertNotNull(newsViewModel.newsHeadlines.value)
//        Assert.assertTrue(newsViewModel.newsHeadlines.value?.size==3)
//
//    }
//
//    private fun mockData(){
//        newsList= emptyList()
//
//        mockNewsList.add(Articles("a","a","a","a","a","a","a"))
//        mockNewsList.add(Articles("a","a","a","a","a","a","a"))
//        mockNewsList.add(Articles("a","a","a","a","a","a","a"))
//
//        newsList= mockNewsList.toList()
//        mockNewsLiveData.postValue(newsList)
//
//    }
//}