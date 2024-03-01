package com.gaurav.movieapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gaurav.movieapp.R
import com.gaurav.movieapp.model.Search
import com.gaurav.movieapp.ui.theme.MovieAppTheme
import com.gaurav.movieapp.ui.viewmodels.MovieViewModel

@Composable
fun HomeScreen(movieViewModel: MovieViewModel, modifier: Modifier = Modifier){

    val movieList = movieViewModel.movieState.collectAsLazyPagingItems()
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(4.dp)
    ){
        items(movieList.itemCount){
            movieList[it]?.let { search ->
                MovieCard(search = search)
            }
        }

        when(movieList.loadState.append){

            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }
            is LoadState.Error -> {
                val e = (movieList.loadState.append as LoadState.Error).error
                Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        when(movieList.loadState.refresh){

            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }
            is LoadState.Error -> {
                val e = (movieList.loadState.refresh as LoadState.Error).error
                Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}

@Composable
fun MovieCard(search: Search, modifier: Modifier = Modifier){

    Card(modifier = modifier
        .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()) {

            Card(modifier = Modifier) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(search.Poster)
                        .crossfade(true)
                        .build(),
                    contentDescription = "poster",
                    error = painterResource(id = R.drawable.ic_broken_image),
                    placeholder = painterResource(id = R.drawable.loading_img),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.
                    size(width = 100.dp, height = 130.dp)
                )
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            ) {

                Text(text = search.Title,
                    style = MaterialTheme.typography.titleLarge)


                Text(text = search.Year,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(vertical = 4.dp))


                Text(text = stringResource(R.string.type, search.Type),
                    style = MaterialTheme.typography.titleMedium)
            }
            
        }
    }

}


@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(42.dp)
                .height(42.dp)
                .padding(8.dp),
            strokeWidth = 5.dp
        )

    }
}


@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun MovieCardPreview(){
   MovieAppTheme {
       MovieCard(search = Search("","Action", Type = "Movie", Year = "1983", ""))
   }
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun MovieListPreview(){
    MovieAppTheme {

    }
}