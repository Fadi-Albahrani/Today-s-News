package com.example.todaysnews.views

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todaysnews.ArticlesApplication
import com.example.todaysnews.R
import com.example.todaysnews.adapters.LatestNewsAdapter
import com.example.todaysnews.models.Article
import com.example.todaysnews.viewModels.ArticleViewModel
import com.example.todaysnews.viewModels.ArticleViewModelFactory

class LatestNewsFragment : Fragment() {

    private val articleViewModel: ArticleViewModel by viewModels {
        ArticleViewModelFactory((requireActivity().application as ArticlesApplication).repository)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_latest_news, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.latest_news_recyclerview)
        val adapter = LatestNewsAdapter(context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        articleViewModel.allOnlineArticles.observe(requireActivity(), Observer {
            adapter.setLatestNewsList(it)

            articleViewModel.deleteAll()

            for (article in it.data){
                val offlineArticle = Article(
                    title = article.title,
                    description = article.description,
                    imageUrl = article.image,
                    category = article.category,
                    publishDate = article.publishedAt,
                    source = article.source
                )

                articleViewModel.insert(offlineArticle)

            }

        })

        articleViewModel.errorMessage.observe(requireActivity(), Observer {

            Log.d(TAG, "onCreateView: there is an error: $it")

        })

        articleViewModel.getLatestArticles()


        return view
    }

}