package com.example.articleapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.articleapplication.model.Article
import com.example.articleapplication.network.EndPoint
import com.example.articleapplication.network.ResultHandler
import com.example.articleapplication.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticleViewModel : ViewModel() {
    private val _liveData = MutableLiveData<ResultHandler<List<Article>>>()
    val liveData: LiveData<ResultHandler<List<Article>>> = _liveData

    fun getResult(endPoint: EndPoint, query: String = "") {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _liveData.postValue(ResultHandler.Loading(true))
                when (endPoint) {
                    EndPoint.ARTICLE -> _liveData.postValue(ResultHandler.Success(getArticles()))
                    EndPoint.SEARCH -> _liveData.postValue(
                        ResultHandler.Success(
                            searchArticles(
                                query
                            )
                        )
                    )
                }
                _liveData.postValue(ResultHandler.Loading(false))
            }
        }
    }

    private suspend fun getArticles(): List<Article> {
        return RetrofitService.retrofitService.getArticles().body()!!
    }


    //query parameters don't work :(
    private suspend fun searchArticles(query: String): List<Article> {
        val body = RetrofitService.retrofitService.getArticles().body()!!
        return body.filter { it.title!!.lowercase().contains(query.lowercase()) }
    }
}