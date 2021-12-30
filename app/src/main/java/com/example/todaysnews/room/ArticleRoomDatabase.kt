package com.example.todaysnews.room


import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todaysnews.models.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Article::class], version = 4)
abstract class ArticleRoomDatabase : RoomDatabase() {


    abstract fun articleDao(): ArticleDao

    private class ArticleDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            Log.d(TAG, "onCreate: call bake called")
            INSTANCE?.let { articleRoomDatabase ->

                scope.launch {
                    var articleDao = articleRoomDatabase.articleDao()
                }

            }

        }
    }

    companion object {

        // Singleton to prevents creating multiple database instances
        @Volatile
        private var INSTANCE: ArticleRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ArticleRoomDatabase {

            Log.d(TAG, "getDatabase: get database called")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleRoomDatabase::class.java,
                    "article_database"
                ).addCallback(ArticleDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance of database
                instance
            }

        }

    }

}