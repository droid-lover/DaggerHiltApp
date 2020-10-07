package com.example.newsapplication.di_dagger.modules

import com.example.newsapplication.repository.NewsRepo
import com.example.newsapplication.utils.C
import com.example.newsapplication.viewmodels.NewsViewModelViewFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Sachin
 */
@Module
class ApiModule constructor(baseUrl: String) {

    var baseUrl: String? = null

    init {
        this.baseUrl = baseUrl
    }

    @Singleton
    @Provides
    fun providesOkHTTP(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(C.TIMEOUT_CONNECT.toLong(), TimeUnit.SECONDS)
            .readTimeout(C.TIMEOUT_READ.toLong(), TimeUnit.SECONDS).build()
    }

    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideNewsRepository(): NewsRepo {
        return NewsRepo()
    }

    @Provides
    fun provideNewsViewModelFactory(): NewsViewModelViewFactory {
        return NewsViewModelViewFactory()
    }
}