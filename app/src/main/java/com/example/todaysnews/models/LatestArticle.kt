package com.example.todaysnews.models

import com.google.gson.annotations.SerializedName

data class LatestArticle(
    @SerializedName("pagination") var pagination: Pagination? = Pagination(),
    @SerializedName("data") var data: ArrayList<OnlineArticleData> = arrayListOf()
)
