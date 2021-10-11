package com.example.newsapp.repositroy

import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.models.NewsResponse
import retrofit2.Response

class NewsRepository(val db: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse> {
        return RetrofitInstance.api.getBrakingNews(countryCode, pageNumber)
    }

}
