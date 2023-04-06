package com.example.gifgallery.domain.search

import com.example.gifgallery.data.local.DbGif
import com.example.gifgallery.data.remote.Gif
import com.example.gifgallery.di.ViewModelCoroutineScope
import com.example.gifgallery.presentation.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    @ViewModelCoroutineScope scope: CoroutineScope
) {
    var query: String = ""
        set(value) {
            field = value
            queryFlow.value = value
        }

    private val queryFlow = MutableStateFlow("")

    private val gifsLocal: Flow<NetworkResult<List<DbGif>>> =
        searchRepository.getGifsFromLocal()
            .map { gifs ->
                NetworkResult.Success(gifs) as NetworkResult<List<DbGif>>
            }
            .catch {
                emit(NetworkResult.Success(emptyList()))
            }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private val gifsRemote: Flow<NetworkResult<List<Gif>>> =
        queryFlow
            .dropWhile { it.isEmpty() }
            .debounce(1000)
            .flatMapLatest { query -> getGifsFromRemote(query) }
            .onStart { emit(NetworkResult.Success(emptyList())) }

    val gifs: StateFlow<NetworkResult<List<DbGif>>> =
        combine(gifsLocal, gifsRemote) { gifLocal, gifRemote ->
            if (gifRemote == NetworkResult.Loading) {
                NetworkResult.Loading
            } else {
                gifLocal
            }
        }
        .catch {
            emit(NetworkResult.Error(it.localizedMessage))
        }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = Long.MAX_VALUE),
            initialValue = NetworkResult.Success(emptyList())
        )

    private fun getGifsFromRemote(query: String): Flow<NetworkResult<List<Gif>>> {
        return searchRepository.getGifsFromRemote(query)
            .map { gifs ->
                val gifsDb = gifs.map { DbGif.fromGif(it) }
                searchRepository.clearGifsFromLocal()
                searchRepository.saveGifsToLocal(gifsDb)
                NetworkResult.Success(gifs) as NetworkResult<List<Gif>>
            }
            .onStart {
                emit(NetworkResult.Loading)
            }
    }
}