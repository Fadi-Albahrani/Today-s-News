package com.example.todaysnews

import android.app.Application
import com.example.todaysnews.api.RetrofitService
import com.example.todaysnews.room.ArticleRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


// this class is responsible about creating one instance of the database and the repo
class ArticlesApplication: Application() {


    val applicationScope = CoroutineScope(SupervisorJob())
    private val retrofitService = RetrofitService.getRSInstance()

    val database by lazy { ArticleRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ArticleRepository(database.articleDao(), retrofitService) }

}