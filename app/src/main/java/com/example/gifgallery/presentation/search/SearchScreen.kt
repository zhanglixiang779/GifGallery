package com.example.gifgallery.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gifgallery.R
import com.example.gifgallery.presentation.NetworkResult
import com.example.gifgallery.utils.ErrorToast
import com.example.gifgallery.utils.Gifs

@Composable
fun SearchScreen(
    paddingValues: PaddingValues,
    viewModel: SearchViewModel = hiltViewModel(),
    navToDetail: (id: String?) -> Unit
) {

    val gifs by viewModel.gifs.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(bottom = paddingValues.calculateBottomPadding())) {
        SearchBar { updatedText ->
            viewModel.query = updatedText
        }
        when (val immutableGifs = gifs) {
            NetworkResult.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }
            }

            is NetworkResult.Success -> {
                Gifs(immutableGifs.data, navToDetail)
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

@Composable
private fun SearchBar(onValueChange: (String) -> Unit) {

    var text by rememberSaveable {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        value = text,
        onValueChange = { updatedText ->
            text = updatedText
            onValueChange(updatedText)
        },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
        placeholder = { Text(text = stringResource(R.string.textfield_placeholder)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}