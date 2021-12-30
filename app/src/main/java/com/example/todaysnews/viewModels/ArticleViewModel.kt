package com.example.todaysnews.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.todaysnews.ArticleRepository
import com.example.todaysnews.models.Article
import com.example.todaysnews.models.LatestArticle
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleViewModel(private val repository: ArticleRepository) : ViewModel() {

    val allOfflineArticles: LiveData<List<Article>> = repository.offlineArticles.asLiveData()
    val allOnlineArticles = MutableLiveData<LatestArticle>()

    val errorMessage = MutableLiveData<String>()

    fun insert(article: Article) = viewModelScope.launch {
        repository.insert(article)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }


    fun getLatestArticles() {
        val response = repository.getLatestArticles()
        response.enqueue(object : Callback<LatestArticle> {
            override fun onResponse(
                call: Call<LatestArticle>,
                response: Response<LatestArticle>
            ) {
                allOnlineArticles.postValue(response.body())

            }

            override fun onFailure(call: Call<LatestArticle>, t: Throwable) {
                Log.d(TAG, "onFailure: the error is: $t")
            }
        })
    }

}

class ArticleViewModelFactory(private val repository: ArticleRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArticleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


