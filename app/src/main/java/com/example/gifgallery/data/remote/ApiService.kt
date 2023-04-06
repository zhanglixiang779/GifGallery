package com.example.gifgallery.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("gifs/search")
    suspend fun getGifs(@Query("q") query: String): ApiResponse

    @GET("gifs/trending")
    suspend fun getTrendingGifs(): ApiResponse

    @GET("gifs/{id}")
    suspend fun getGif(@Path("id") id: String): GifResponse
}