package com.example.gifgallery.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gifgallery.presentation.detail.DetailScreen
import com.example.gifgallery.presentation.search.SearchScreen
import com.example.gifgallery.presentation.trending.TrendingScreen

@Composable
fun ApplicationScaffold(
    navController: NavHostController = rememberNavController()
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    val shouldShowBottomBar = currentDestination?.route == Destination.Search.route
            || currentDestination?.route == Destination.Trending.route

    Scaffold(
        scaffoldState = rememberScaffoldState(),
        bottomBar = {
            if (shouldShowBottomBar) {
                AppBottomNavBar(
                    onSearchClicked = {
                        navController.navigate(Destination.Search.route) {
                            popUpTo(Destination.Search.route)
                            launchSingleTop = true
                        }
                    },
                    onTrendingClicked = {
                        navController.navigate(Destination.Trending.route) {
                            launchSingleTop = true
                        }
                    },
                    currentDestination = currentDestination
                )
            }
        }
    ) { paddingValues ->

        NavHost(navController = navController, startDestination = Destination.Search.route) {
            composable(Destination.Search.route) {
                SearchScreen(paddingValues) { id ->
                    id?.let {
                        navController.navigate(Destination.Detail.navigateToDetail(it))
                    }
                }
            }

            composable(Destination.Trending.route) {
                TrendingScreen(paddingValues) { id ->
                    id?.let {
                        navController.navigate(Destination.Detail.navigateToDetail(it))
                    }
                }
            }

            composable(
                route = Destination.Detail.route,
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) { navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getString("id").orEmpty()
                DetailScreen(id)
            }
        }
    }
}