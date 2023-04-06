package com.example.gifgallery.data.remote

import com.example.gifgallery.domain.detail.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultDetailRepository @Inject constructor(
    private val apiService: ApiService
) : DetailRepository {

    override fun getGif(id: String): Flow<Gif> {
        return flow {
            emit(apiService.getGif(id).data ?: Gif())
        }
    }
}