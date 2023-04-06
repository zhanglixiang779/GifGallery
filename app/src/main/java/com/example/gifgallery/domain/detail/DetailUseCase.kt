package com.example.gifgallery.domain.detail

import com.example.gifgallery.data.remote.Gif
import com.example.gifgallery.di.ViewModelCoroutineScope
import com.example.gifgallery.presentation.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    private val detailRepository: DetailRepository,
    @ViewModelCoroutineScope scope: CoroutineScope
) {
    var id: String = ""
        set(value) {
            field = value
            idFlow.value = value
        }

    private val idFlow = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    val gif: StateFlow<NetworkResult<Gif>> =
        idFlow
            .flatMapConcat {
                getGif(id)
            }
            .stateIn(
                scope = scope,
                started = SharingStarted.WhileSubscribed(
                    stopTimeoutMillis = 5000L,
                    replayExpirationMillis = 0L
                ),
                initialValue = NetworkResult.Loading
            )

    private fun getGif(id: String): Flow<NetworkResult<Gif>> {
        return detailRepository.getGif(id)
            .map { gif ->
                NetworkResult.Success(gif) as NetworkResult<Gif>
            }
            .catch {
                emit(NetworkResult.Error(it.localizedMessage))
            }
    }
}