package com.gaurav.movieapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gaurav.movieapp.model.Search
import com.gaurav.movieapp.repository.MoviesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MoviesRepository):ViewModel() {

    private var _movieState:MutableStateFlow<PagingData<Search>> = MutableStateFlow(value = PagingData.empty())
    val movieState:MutableStateFlow<PagingData<Search>> get() = _movieState


    init {
        getMovieList()
    }

    private fun getMovieList(){
        viewModelScope.launch {
            repository.getMovies()
                .distinctUntilChanged()
                .cachedIn(this)
                .collect{
                    _movieState.value = it
                }
        }
    }

}