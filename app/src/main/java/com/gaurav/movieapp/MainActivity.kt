package com.gaurav.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gaurav.movieapp.ui.screens.HomeScreen
import com.gaurav.movieapp.ui.theme.MovieAppTheme
import com.gaurav.movieapp.ui.viewmodels.MovieViewModel
import com.gaurav.movieapp.utils.AppViewModelProvider

class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewmodel:MovieViewModel = viewModel(factory= AppViewModelProvider.factory)
                    
                    HomeScreen(movieViewModel = viewmodel)
                }
            }
        }
    }
}

