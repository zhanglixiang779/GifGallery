package com.example.gifgallery.domain.detail

import com.example.gifgallery.data.remote.Gif
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getGif(id: String): Flow<Gif>
}