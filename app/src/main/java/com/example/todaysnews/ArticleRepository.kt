package com.example.todaysnews

import com.example.todaysnews.api.RetrofitService
import com.example.todaysnews.models.Article
import com.example.todaysnews.room.ArticleDao
import kotlinx.coroutines.flow.Flow

class ArticleRepository(private val articleDao: ArticleDao, private val retrofitService: RetrofitService) {

    val offlineArticles: Flow<List<Article>> = articleDao.getArticles()

    @Suppress("RedundantSuspendModifier")
    suspend fun insert(article: Article) {
        articleDao.insert(article)
    }

    suspend fun deleteAll(){
        articleDao.deleteAll()
    }

    fun getLatestArticles() = retrofitService.getLatestArticles(accessKey = "7c6d5ce93810f136052237ddef2bdc08", countries = "us")

}