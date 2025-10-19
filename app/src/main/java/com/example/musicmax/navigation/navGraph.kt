package com.example.musicmax.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.musicmax.ui.detail.DetailScreen
import com.example.musicmax.ui.home.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable(
            route = "detail/{albumId}",
            arguments = listOf(navArgument("albumId") { type = NavType.StringType })
        ) { backStack ->
            val albumId = backStack.arguments?.getString("albumId") ?: ""
            DetailScreen(albumId, navController)
        }
    }
}
