package com.example.gifgallery.data

import com.example.gifgallery.data.remote.ApiResponse
import com.example.gifgallery.data.remote.ApiService
import com.example.gifgallery.data.remote.Gif
import com.example.gifgallery.data.remote.GifResponse

class FakeApiService : ApiService {

    private val gif1 = Gif("1", "cat", null, null)
    private val gif2 = Gif("2", "cat", null, null)
    private val gif3 = Gif("3", "cat", null, null)
    private val gif4 = Gif("4", "cat", null, null)
    val gifs = mutableListOf(gif1, gif2, gif3, gif4)

    override suspend fun getGifs(query: String): ApiResponse {
        return ApiResponse(data = gifs)
    }

    override suspend fun getTrendingGifs(): ApiResponse {
        return ApiResponse(data = gifs)
    }

    override suspend fun getGif(id: String): GifResponse {
        return GifResponse(data = gif1)
    }
}