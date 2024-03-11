@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.jetmovieapp.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetmovieapp.model.Movie
import com.example.jetmovieapp.model.getMovies
import com.example.jetmovieapp.nav.MovieViewEnum
import com.example.jetmovieapp.widgets.MovieRow

@Composable
fun HomeView(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier
                            .size(48.dp)
                            .fillMaxWidth()
                            .padding(end = 8.dp),
                        imageVector = Icons.Filled.Home,
                        contentDescription = "profile picture",
                    )
                    Text("Movies")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primaryContainer),
        )
    },
        content = { padding ->
            MainContent(navController, padding)
        }
    )
}

@Composable
fun MainContent(
    navController: NavController,
    padding: PaddingValues = PaddingValues(),
    movieList: List<Movie> = getMovies()
) {
    Column(modifier = Modifier.padding(padding)) {
        LazyColumn(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
        ) {
            items(items = movieList) {
                MovieRow(movie = it) { movie ->
                    navController.navigate(route = MovieViewEnum.DETAIL.name + "/$movie")
                }
            }
        }
    }
}