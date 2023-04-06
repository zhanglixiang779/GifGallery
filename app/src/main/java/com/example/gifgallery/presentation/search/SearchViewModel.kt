package com.example.gifgallery.presentation.search

import androidx.lifecycle.ViewModel
import com.example.gifgallery.domain.Gif
import com.example.gifgallery.domain.NetworkResult
import com.example.gifgallery.domain.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: SearchUseCase,
) : ViewModel() {

    var query: String = ""
        set(value) {
            field = value
            useCase.query = value
        }

    val gifs: StateFlow<NetworkResult<List<Gif>>> = useCase.gifs
}