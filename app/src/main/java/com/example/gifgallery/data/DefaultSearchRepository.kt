package com.example.gifgallery.data

import com.example.gifgallery.data.local.DbGif
import com.example.gifgallery.data.local.GifDao
import com.example.gifgallery.data.remote.ApiService
import com.example.gifgallery.data.remote.Gif
import com.example.gifgallery.domain.search.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultSearchRepository @Inject constructor(
    private val apiService: ApiService,
    private val gifDao: GifDao
) : SearchRepository {

    override fun getGifsFromRemote(query: String): Flow<List<Gif>> {
        return flow {
            emit(apiService.getGifs(query).data ?: emptyList())
        }
    }

    override fun getGifsFromLocal(): Flow<List<DbGif>> {
        return gifDao.getGifs()
    }

    override suspend fun saveGifsToLocal(gifs: List<DbGif>) {
        gifDao.saveGifs(gifs)
    }

    override suspend fun clearGifsFromLocal() {
        gifDao.clearGifs()
    }
}