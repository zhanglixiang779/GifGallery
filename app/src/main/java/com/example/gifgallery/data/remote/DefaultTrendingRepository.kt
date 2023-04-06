package com.example.gifgallery.data.remote

import com.example.gifgallery.domain.trending.TrendingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultTrendingRepository @Inject constructor(
    private val apiService: ApiService
) : TrendingRepository {

    override fun getTrendingGifs(): Flow<List<Gif>> {
        return flow {
            emit(apiService.getTrendingGifs().data ?: emptyList())
        }
    }
}