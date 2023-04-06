package com.example.gifgallery.domain.search

import com.example.gifgallery.data.local.DbGif
import com.example.gifgallery.data.remote.Gif
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun getGifsFromRemote(query: String): Flow<List<Gif>>

    fun getGifsFromLocal(): Flow<List<DbGif>>

    suspend fun clearGifsFromLocal()

    suspend fun saveGifsToLocal(gifs: List<DbGif>)
}