package com.example.todaysnews.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todaysnews.R
import com.example.todaysnews.adapters.OfflineNewsAdapter.ArticleViewHolder
import com.example.todaysnews.models.Article
import com.example.todaysnews.views.ArticleDetailActivity
import com.example.todaysnews.views.OfflineArticleDetailActivity
import kotlinx.coroutines.NonDisposableHandle.parent

class OfflineNewsAdapter(var context: Context?): ListAdapter<Article,ArticleViewHolder>(ArticleComparator())  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.title, current.category, current.source)

        holder.itemView.setOnClickListener{
            val intent = Intent(context, OfflineArticleDetailActivity::class.java)

            intent.putExtra("article", current)
            context?.startActivity(intent)
        }


    }

    class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val articleTitleView: TextView = itemView.findViewById(R.id.article_title)
        private val articleSourceView: TextView = itemView.findViewById(R.id.article_source)
        private val articleCategoryView: TextView = itemView.findViewById(R.id.article_category)

        fun bind(title: String?, category: String?, source: String?) {
            articleTitleView.text = title
            articleSourceView.text = source
            articleCategoryView.text = category
        }

        companion object {
            fun create(parent: ViewGroup): ArticleViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.article_item, parent, false)

                return ArticleViewHolder(view)
            }
        }

    }

    class ArticleComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }
    }




}