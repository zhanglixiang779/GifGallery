package com.example.gifgallery.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gifgallery.domain.Gif

@Entity(tableName = "gifs_table")
data class LocalGif(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val gifId: String?,
    val title: String? = null,
    val url: String? = null
) {
    companion object {

        fun fromGif(gif: Gif): LocalGif {
            return LocalGif(
                id = 0,
                gifId = gif.id,
                title = gif.title,
                url = gif.urlSmall
            )
        }

        fun toGif(localGif: LocalGif): Gif {
            return Gif(
                id = localGif.gifId,
                title = localGif.title,
                urlSmall = localGif.url
            )
        }
    }
}