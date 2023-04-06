package com.example.gifgallery.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gifgallery.data.remote.Gif

@Entity(tableName = "gifs_table")
data class DbGif(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val gifId: String?,
    val title: String? = null,
    val url: String? = null
) {
    companion object {
        fun fromGif(gif: Gif): DbGif {
            return DbGif(
                id = 0,
                gifId = gif.id,
                title = gif.title,
                url = gif.images?.fixed_height?.url
            )
        }
    }
}