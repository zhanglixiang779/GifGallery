package com.example.gifgallery.domain

data class Gif(
    val id: String? = null,
    val title: String? = null,
    val urlSmall: String? = null,
    val urlLarge: String? = null,
    val displayName: String? = null,
    val description: String? = null,
    val avatarUrl: String? = null
)
