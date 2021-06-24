package com.example.articleapplication.network

import com.example.articleapplication.model.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleApi {

    @GET("articles")
    suspend fun getArticles():Response<List<Article>>

    @GET("articles")
    suspend fun searchArticle(@Path("id") id: Int):Response<Article>

}