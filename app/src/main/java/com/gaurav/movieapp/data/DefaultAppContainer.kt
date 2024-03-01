package com.gaurav.movieapp.data

import com.gaurav.movieapp.BuildConfig
import com.gaurav.movieapp.repository.MoviesRepository
import com.gaurav.movieapp.utils.Constants
import com.gaurav.movieapp.webservice.MovieApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DefaultAppContainer():AppContainer {

    val requestInterceptor = Interceptor{ chain ->

        val urls = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("apikey", Constants.API_KEY)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(urls)
            .build()

        return@Interceptor chain.proceed(request)

    }

    private fun getClient() = if(BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(getClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val movieApiService:MovieApiService by lazy{
        retrofit.create(MovieApiService::class.java)
    }


    override val moviesRepository: MoviesRepository by lazy {
        MoviesRepositoryImpl(movieApiService)
    }
}