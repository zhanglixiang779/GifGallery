package com.example.gifgallery.presentation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.example.gifgallery.R

@Composable
fun AppBottomNavBar(
    onSearchClicked: () -> Unit,
    onTrendingClicked: () -> Unit,
    currentDestination: NavDestination?,
) {
    BottomNavigation(elevation = 5.dp) {
        val iconSearch = painterResource(id = R.drawable.ic_search)
        val iconTrending = painterResource(id = R.drawable.ic_trending)

        BottomNavigationItem(
            selected = currentDestination?.route == Destination.Search.route,
            onClick = onSearchClicked,
            icon = { Icon(painter = iconSearch, contentDescription = null) },
            label = { Text(Destination.Search.route) }
        )

        BottomNavigationItem(
            selected = currentDestination?.route == Destination.Trending.route,
            onClick = onTrendingClicked,
            icon = { Icon(painter = iconTrending, contentDescription = null) },
            label = { Text(Destination.Trending.route) }
        )
    }
}