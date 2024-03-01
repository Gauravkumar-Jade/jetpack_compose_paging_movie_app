package com.gaurav.movieapp

import android.app.Application
import android.content.Context
import com.gaurav.movieapp.data.AppContainer
import com.gaurav.movieapp.data.DefaultAppContainer

class MovieApplication:Application() {

    lateinit var container: AppContainer
    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
        context = this
    }
}