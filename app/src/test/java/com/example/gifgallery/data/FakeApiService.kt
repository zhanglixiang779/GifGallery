package com.example.gifgallery.data

import com.example.gifgallery.data.remote.ApiService
import com.example.gifgallery.data.remote.RemoteGif
import com.example.gifgallery.data.remote.RemoteGifResponse
import com.example.gifgallery.data.remote.RemoteGifsResponse

class FakeApiService : ApiService {

    private val gif1 = RemoteGif("1", "cat", null, null)
    private val gif2 = RemoteGif("2", "cat", null, null)
    private val gif3 = RemoteGif("3", "cat", null, null)
    private val gif4 = RemoteGif("4", "cat", null, null)
    val gifs = mutableListOf(gif1, gif2, gif3, gif4)

    override suspend fun getGifs(query: String): RemoteGifsResponse {
        return RemoteGifsResponse(data = gifs)
    }

    override suspend fun getTrendingGifs(): RemoteGifsResponse {
        return RemoteGifsResponse(data = gifs)
    }

    override suspend fun getGif(id: String): RemoteGifResponse {
        return RemoteGifResponse(data = gif1)
    }
}