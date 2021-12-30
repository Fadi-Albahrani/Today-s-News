package com.example.todaysnews.api

import com.example.todaysnews.models.LatestArticle
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("news")
    fun getLatestArticles(@Query("access_key") accessKey: String, @Query("countries") countries: String): Call<LatestArticle>

    companion object {
        var retrofitService: RetrofitService? = null

        fun getRSInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder().baseUrl("http://api.mediastack.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }

}