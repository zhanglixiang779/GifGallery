package com.example.gifgallery.data.remote

data class ApiResponse(
    val data: List<Gif>? = null
)

data class GifResponse(
    val data: Gif? = null
)

data class Gif(
    val id: String? = null,
    val title: String? = null,
    val images: Images? = null,
    val user: User? = null
)

data class Images(
    val fixed_height: FixedHeight? = null,
    val downsized_large: DownsizedLarge? = null
)

data class User(
    val avatar_url: String? = null,
    val username: String? = null,
    val display_name: String? = null,
    val description: String? = null
)

data class FixedHeight(
    val url: String? = null,
    val width: String? = null,
    val height: String? = null
)

data class DownsizedLarge(
    val url: String? = null,
    val width: String? = null,
    val height: String? = null
)