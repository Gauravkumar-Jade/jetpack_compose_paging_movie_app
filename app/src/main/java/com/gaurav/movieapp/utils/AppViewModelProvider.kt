package com.gaurav.movieapp.utils

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.gaurav.movieapp.MovieApplication
import com.gaurav.movieapp.ui.viewmodels.MovieViewModel

object AppViewModelProvider {

    val factory = viewModelFactory {
        initializer {
            MovieViewModel(movieApplication().container.moviesRepository)
        }
    }

    private fun CreationExtras.movieApplication():MovieApplication{
        return (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApplication)
    }

}