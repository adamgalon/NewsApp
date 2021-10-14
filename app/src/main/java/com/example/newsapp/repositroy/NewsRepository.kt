package com.example.newsapp.repositroy

import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.models.Article
import com.example.newsapp.models.NewsResponse
import retrofit2.Response

class NewsRepository(val db: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse> {
        return RetrofitInstance.api.getBrakingNews(countryCode, pageNumber)
    }


    suspend fun getSearchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getAllSavedArticles() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}
