package com.example.articleapplication.model

data class Article(
    val id: Int?,
    val featured: Boolean = false,
    val title: String?,
    val url: String?,
    val imageUrl: String?,
    val newsSite: String?,
    val summary: String?,
    val publishedAt: String?,
    val updatedAt: String?,
    val launches:List<Info>,
    val events:List<Info>
)

data class Info(
    val id: String?,
    val provider: String?
)