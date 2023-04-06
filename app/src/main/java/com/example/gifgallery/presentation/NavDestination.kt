package com.example.gifgallery.presentation

sealed class Destination(val route: String) {
    object Search : Destination("search")
    object Trending : Destination("trending")
    object Detail : Destination("detail/{id}") {
        fun navigateToDetail(id: String) = "detail/$id"
    }
}