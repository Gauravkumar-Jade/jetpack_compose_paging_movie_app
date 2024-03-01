package com.gaurav.movieapp.webservice

import com.gaurav.movieapp.model.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("?s=action")
    suspend fun getMoviesData(@Query("page") page: Int):Movies
}