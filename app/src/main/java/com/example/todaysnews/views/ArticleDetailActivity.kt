package com.example.todaysnews.views

import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.todaysnews.ArticlesApplication
import com.example.todaysnews.R
import com.example.todaysnews.databinding.ActivityArticleDetailBinding
import com.example.todaysnews.models.OnlineArticleData
import com.example.todaysnews.viewModels.ArticleViewModel
import com.example.todaysnews.viewModels.ArticleViewModelFactory

class ArticleDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityArticleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val articleImageView = binding.detailArticleImage
        val articleTitleView = binding.detailArticleTitle
        val articleSourceView = binding.detailArticleSource
        val articleCategoryView = binding.detailArticleCategory
        val articleDescriptionView = binding.articleDescription

        Log.d(TAG, "onCreate: the data is: ${intent.getParcelableExtra<OnlineArticleData>("article")}")

        val article = intent.getParcelableExtra<OnlineArticleData>("article")

        showArticleImage(articleImageView, article!!.image)
        articleTitleView.text = article.title
        articleSourceView.text = article.source
        articleCategoryView.text = article.category
        articleDescriptionView.text = article.description
    }

    private fun showArticleImage(imageView: ImageView, imageUrl: String?){

        val circularProgressDrawable = CircularProgressDrawable(applicationContext)

        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(circularProgressDrawable)
            .error(R.drawable.no_picture_available)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)

        Glide.with(applicationContext).load(imageUrl).apply(options).into(imageView)

    }

}