package com.example.gifgallery.presentation.detail

import androidx.lifecycle.ViewModel
import com.example.gifgallery.domain.Gif
import com.example.gifgallery.domain.NetworkResult
import com.example.gifgallery.domain.detail.DetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: DetailUseCase
) : ViewModel() {

    var id: String = ""
        set(value) {
            field = value
            useCase.id = value
        }

    val gifFlow: StateFlow<NetworkResult<Gif>> = useCase.gif
}