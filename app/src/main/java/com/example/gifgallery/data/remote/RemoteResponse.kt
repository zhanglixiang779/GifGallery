package com.example.gifgallery.data.remote

import com.example.gifgallery.domain.Gif

data class RemoteGifsResponse(
    val data: List<RemoteGif>? = null
)

data class RemoteGifResponse(
    val data: RemoteGif? = null
)

data class RemoteGif(
    val id: String? = null,
    val title: String? = null,
    val images: RemoteImages? = null,
    val user: RemoteUser? = null
) {
    companion object {

        fun toGifSmall(remoteGif: RemoteGif): Gif {
            return Gif(
                id = remoteGif.id,
                title = remoteGif.title,
                urlSmall = remoteGif.images?.fixed_height?.url,
                displayName = remoteGif.user?.display_name,
                description = remoteGif.user?.description,
                avatarUrl = remoteGif.user?.avatar_url
            )
        }

        fun toGifLarge(remoteGif: RemoteGif): Gif {
            return Gif(
                id = remoteGif.id,
                title = remoteGif.title,
                urlLarge = remoteGif.images?.downsized_large?.url,
                displayName = remoteGif.user?.display_name,
                description = remoteGif.user?.description,
                avatarUrl = remoteGif.user?.avatar_url
            )
        }
    }
}

data class RemoteImages(
    val fixed_height: RemoteFixedHeight? = null,
    val downsized_large: RemoteDownsizedLarge? = null
)

data class RemoteUser(
    val avatar_url: String? = null,
    val username: String? = null,
    val display_name: String? = null,
    val description: String? = null
)

data class RemoteFixedHeight(
    val url: String? = null,
    val width: String? = null,
    val height: String? = null
)

data class RemoteDownsizedLarge(
    val url: String? = null,
    val width: String? = null,
    val height: String? = null
)