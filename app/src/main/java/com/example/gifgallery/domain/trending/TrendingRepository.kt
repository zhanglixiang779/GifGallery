package com.example.gifgallery.domain.trending

import com.example.gifgallery.domain.Gif
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {
    fun getTrendingGifs(): Flow<List<Gif>>
}