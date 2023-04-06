package com.example.gifgallery.data

import com.example.gifgallery.data.local.DbGif
import com.example.gifgallery.data.local.GifDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeGifDao @Inject constructor() : GifDao {

    val gifs: MutableList<DbGif> = mutableListOf()

    override suspend fun saveGifs(gifs: List<DbGif>) {
        this.gifs.addAll(gifs)
    }

    override suspend fun clearGifs() {
        this.gifs.clear()
    }

    override fun getGifs(): Flow<List<DbGif>> {
        return flow {
            emit(gifs)
        }
    }
}