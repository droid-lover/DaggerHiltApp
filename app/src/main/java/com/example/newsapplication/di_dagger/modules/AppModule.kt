package com.example.newsapplication.di_dagger.modules


import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Sachin
 */
@Module
class AppModule constructor(context: Context) {

     var context: Context = context

    @Provides
    fun context(): Context{
        return context
    }

}
