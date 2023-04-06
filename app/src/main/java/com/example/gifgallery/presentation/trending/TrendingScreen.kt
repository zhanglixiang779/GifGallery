package com.example.gifgallery.presentation.trending

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gifgallery.data.local.DbGif
import com.example.gifgallery.presentation.NetworkResult
import com.example.gifgallery.utils.ErrorToast
import com.example.gifgallery.utils.Gifs

@Composable
fun TrendingScreen(
    paddingValues: PaddingValues,
    viewModel: TrendingViewModel = hiltViewModel(),
    navToDetail: (id: String?) -> Unit
) {
    val trendingGifs by viewModel.trendingGifs.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = paddingValues.calculateBottomPadding())) {

        when (val immutableGifs = trendingGifs) {
            NetworkResult.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }
            }

            is NetworkResult.Success -> {
                val gifs = immutableGifs.data.map { DbGif.fromGif(it) }
                Gifs(gifs, navToDetail)
            }

            is NetworkResult.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { ErrorToast(immutableGifs.message) }
            }
        }
    }
}