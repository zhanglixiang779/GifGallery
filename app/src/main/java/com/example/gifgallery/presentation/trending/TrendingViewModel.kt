package com.example.gifgallery.presentation.trending

import androidx.lifecycle.ViewModel
import com.example.gifgallery.data.remote.Gif
import com.example.gifgallery.di.AppCoroutineScope
import com.example.gifgallery.domain.trending.TrendingRepository
import com.example.gifgallery.presentation.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    trendingRepository: TrendingRepository,
    @AppCoroutineScope scope: CoroutineScope
) : ViewModel() {

    val trendingGifs: StateFlow<NetworkResult<List<Gif>>> = trendingRepository.getTrendingGifs()
        .map { gifs ->
            NetworkResult.Success(gifs) as NetworkResult<List<Gif>>
        }
        .catch {
            emit(NetworkResult.Error(it.localizedMessage))
        }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = Long.MAX_VALUE),
            initialValue = NetworkResult.Loading
        )
}