package com.gaurav.movieapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gaurav.movieapp.model.Search
import com.gaurav.movieapp.webservice.MovieApiService
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(private val movieApiService: MovieApiService):PagingSource<Int, Search>() {


    override fun getRefreshKey(state: PagingState<Int, Search>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {
        return try{
            val currentPage = params.key ?: 1
            val movies = movieApiService.getMoviesData(page = currentPage)

            LoadResult.Page(
                data = movies.Search,
                prevKey = if(currentPage == 1) null else currentPage.minus(1),
                nextKey = if(movies.Search.isEmpty()) null else currentPage.plus(1)
            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }
        catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}