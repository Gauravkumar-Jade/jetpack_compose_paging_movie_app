package com.gaurav.movieapp.repository

import androidx.paging.PagingData
import com.gaurav.movieapp.model.Search
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getMovies(): Flow<PagingData<Search>>
}