package com.gaurav.movieapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gaurav.movieapp.repository.MoviesRepository
import com.gaurav.movieapp.model.Search
import com.gaurav.movieapp.paging.MoviePagingSource
import com.gaurav.movieapp.webservice.MovieApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl(private val movieApiService: MovieApiService): MoviesRepository {

    override suspend fun getMovies(): Flow<PagingData<Search>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                MoviePagingSource(movieApiService)
            }
        ).flow
    }
}