package com.example.gifgallery.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gifgallery.domain.NetworkResult
import com.example.gifgallery.utils.ErrorToast
import com.example.gifgallery.utils.GifImage

@Composable
fun DetailScreen(
    id: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val gifResult by viewModel.gifFlow.collectAsState()

    var isGifLoading by remember {
        mutableStateOf(true)
    }

    viewModel.id = id

    when (val gif = gifResult) {
        NetworkResult.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is NetworkResult.Success -> {
            Column(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
                val title = gif.data.title
                val displayName = gif.data.displayName
                val description = gif.data.description
                val avatarUrl = gif.data.avatarUrl

                title?.let {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Title: $it",
                        fontSize = 18.sp
                    )
                }

                description?.let {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Description: $it",
                        fontSize = 18.sp
                    )
                }

                displayName?.let {
                    Row(modifier = Modifier.padding(8.dp)) {
                        avatarUrl?.let { url ->
                            GifImage(
                                url = url,
                                modifier = Modifier.clip(CircleShape).size(44.dp)
                            )
                        }
                        Text(modifier = Modifier.padding(8.dp), text = it, fontWeight = FontWeight.Bold)
                    }
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (isGifLoading) {
                        CircularProgressIndicator()
                    }
                    GifImage(
                        url = gif.data.urlLarge,
                        modifier = Modifier.fillMaxWidth(),
                        onSuccess = { isGifLoading = false }
                    )
                }
            }
        }

        is NetworkResult.Error -> {
            ErrorToast(gif.message)
        }
    }
}