package com.gaurav.movieapp.data

import com.gaurav.movieapp.repository.MoviesRepository

interface AppContainer {
    val moviesRepository: MoviesRepository
}
