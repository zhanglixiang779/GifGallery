package com.example.gifgallery.data.remote

import com.example.gifgallery.domain.Gif
import com.example.gifgallery.domain.detail.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultDetailRepository @Inject constructor(
    private val apiService: ApiService
) : DetailRepository {

    override fun getGif(id: String): Flow<Gif> {
        return flow {
            val gif = RemoteGif.toGifLarge(apiService.getGif(id).data ?: RemoteGif())
            emit(gif)
        }
    }
}