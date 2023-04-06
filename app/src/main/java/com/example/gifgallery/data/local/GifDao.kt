package com.example.gifgallery.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GifDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveGifs(gifs: List<LocalGif>)

    @Query("DELETE FROM gifs_table")
    suspend fun clearGifs()

    @Query("SELECT * FROM gifs_table")
    fun getGifs(): Flow<List<LocalGif>>
}