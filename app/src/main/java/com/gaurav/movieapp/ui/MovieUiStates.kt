package com.gaurav.movieapp.ui

import com.gaurav.movieapp.model.Search

sealed interface MovieUiStates {

    data class Success(val searchList: List<Search>): MovieUiStates
    object Loading:MovieUiStates
    data class HttpError(val code:Int?, val message: String?):MovieUiStates
    data class Error(val message: String?): MovieUiStates
}