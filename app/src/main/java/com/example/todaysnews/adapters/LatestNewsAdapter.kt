package com.example.todaysnews.adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.todaysnews.databinding.ArticleItemBinding
import com.example.todaysnews.models.LatestArticle
import com.example.todaysnews.models.OnlineArticleData
import com.bumptech.glide.load.engine.DiskCacheStrategy

import android.R
import android.content.Context
import android.content.Intent
import android.view.View
import com.bumptech.glide.Priority

import com.bumptech.glide.request.RequestOptions
import com.example.todaysnews.views.ArticleDetailActivity


class LatestNewsAdapter(var context: Context?) : RecyclerView.Adapter<LatestNewsViewHolder>() {

    var latestNews = mutableListOf<OnlineArticleData>()
    var circularProgressDrawable: CircularProgressDrawable? = null


    fun setLatestNewsList(latestNews: LatestArticle){
        this.latestNews = latestNews.data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LatestNewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ArticleItemBinding.inflate(inflater, parent, false)
        circularProgressDrawable = CircularProgressDrawable(parent.context)
        return LatestNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LatestNewsViewHolder, position: Int) {
        val article = latestNews[position]
        holder.binding.articleTitle.text = article.title
        holder.binding.articleSource.text = article.source
        holder.binding.articleCategory.text = article.category



        holder.binding.root.setOnClickListener {
            val intent = Intent(context, ArticleDetailActivity::class.java)

            intent.putExtra("article", article)
            context?.startActivity(intent)
        }

        circularProgressDrawable!!.strokeWidth = 5f
        circularProgressDrawable!!.centerRadius = 30f
        circularProgressDrawable!!.start()


        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(circularProgressDrawable)
            .error(com.example.todaysnews.R.drawable.no_picture_available)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)

        Glide.with(holder.itemView.context).load(article.image).apply(options).into(holder.binding.articleImage)
    }

    override fun getItemCount(): Int {
        return latestNews.size
    }


}

class LatestNewsViewHolder (val binding: ArticleItemBinding) : RecyclerView.ViewHolder(binding.root) {



}