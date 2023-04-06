package com.example.gifgallery.domain.search

import com.example.gifgallery.domain.Gif
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun getGifsFromRemote(query: String): Flow<List<Gif>>

    fun getGifsFromLocal(): Flow<List<Gif>>

    suspend fun clearGifsFromLocal()

    suspend fun saveGifsToLocal(gifs: List<Gif>)
}