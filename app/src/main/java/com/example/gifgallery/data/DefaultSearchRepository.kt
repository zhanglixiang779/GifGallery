package com.example.gifgallery.data

import com.example.gifgallery.data.local.GifDao
import com.example.gifgallery.data.local.LocalGif
import com.example.gifgallery.data.remote.ApiService
import com.example.gifgallery.data.remote.RemoteGif
import com.example.gifgallery.domain.Gif
import com.example.gifgallery.domain.search.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultSearchRepository @Inject constructor(
    private val apiService: ApiService,
    private val gifDao: GifDao
) : SearchRepository {

    override fun getGifsFromRemote(query: String): Flow<List<Gif>> {
        return flow {
            val gifs = apiService.getGifs(query).data?.map { RemoteGif.toGifSmall(it) }
            emit(gifs ?: emptyList())
        }
    }

    override fun getGifsFromLocal(): Flow<List<Gif>> {
        return gifDao.getGifs().map { localGifs ->
            localGifs.map { LocalGif.toGif(it) }
        }
    }

    override suspend fun saveGifsToLocal(gifs: List<Gif>) {
        val localGifs = gifs.map { LocalGif.fromGif(it) }
        gifDao.saveGifs(localGifs)
    }

    override suspend fun clearGifsFromLocal() {
        gifDao.clearGifs()
    }
}