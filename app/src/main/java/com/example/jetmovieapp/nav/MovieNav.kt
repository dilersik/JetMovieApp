package com.example.jetmovieapp.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetmovieapp.view.detail.DetailView
import com.example.jetmovieapp.view.home.HomeView

@Composable
fun MovieNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieViewEnum.HOME.name) {
        composable(MovieViewEnum.HOME.name) {
            HomeView(navController)
        }

        composable(
            MovieViewEnum.DETAIL.name + "/{movie}",
            arguments = listOf(navArgument(name = "movie") {
                type = NavType.StringType
            })
        ) {
            DetailView(navController, movieData = it.arguments?.getString("movie"))
        }
    }
}