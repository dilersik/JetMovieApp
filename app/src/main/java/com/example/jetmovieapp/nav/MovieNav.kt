package com.example.jetmovieapp.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetmovieapp.view.detail.DetailView
import com.example.jetmovieapp.view.home.HomeView

@Composable
fun MovieNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieViewEnum.HOME.name) {
        composable(MovieViewEnum.HOME.name) {
            HomeView(navController)
        }

        composable(MovieViewEnum.DETAIL.name) {
            DetailView(navController)
        }
    }
}