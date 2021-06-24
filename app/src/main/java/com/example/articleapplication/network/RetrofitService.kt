package com.example.articleapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val BASE_URL = "https://api.spaceflightnewsapi.net/v3/"

    val retrofitService: ArticleApi by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(ArticleApi::class.java)
    }
}