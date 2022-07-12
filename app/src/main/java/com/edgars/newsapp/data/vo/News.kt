package com.edgars.newsapp.data.vo

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)