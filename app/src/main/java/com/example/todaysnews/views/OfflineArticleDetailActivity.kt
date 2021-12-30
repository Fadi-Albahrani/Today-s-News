package com.example.todaysnews.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todaysnews.R
import com.example.todaysnews.databinding.ActivityArticleDetailBinding
import com.example.todaysnews.databinding.ActivityOfflineArticleDetailBinding
import com.example.todaysnews.models.Article
import com.example.todaysnews.models.OnlineArticleData

class OfflineArticleDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityOfflineArticleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOfflineArticleDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val articleTitleView = binding.detailArticleTitle
        val articleSourceView = binding.detailArticleSource
        val articleCategoryView = binding.detailArticleCategory
        val articleDescriptionView = binding.articleDescription

        val article = intent.getParcelableExtra<Article>("article")


        articleTitleView.text = article?.title
        articleSourceView.text = article?.source
        articleCategoryView.text = article?.category
        articleDescriptionView.text = article?.description

    }
}