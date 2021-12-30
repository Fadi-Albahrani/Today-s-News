package com.example.todaysnews.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todaysnews.ArticlesApplication
import com.example.todaysnews.R
import com.example.todaysnews.adapters.OfflineNewsAdapter
import com.example.todaysnews.models.Article
import com.example.todaysnews.viewModels.ArticleViewModel
import com.example.todaysnews.viewModels.ArticleViewModelFactory

class OfflineNewsFragment : Fragment() {

    private val articleViewModel: ArticleViewModel by viewModels {
        ArticleViewModelFactory((requireActivity().application as ArticlesApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_offline_news, container, false)


        val recyclerView = view.findViewById<RecyclerView>(R.id.offline_news_recyclerview)
        val adapter = OfflineNewsAdapter(context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        articleViewModel.allOfflineArticles.observe(requireActivity()) {
                articles ->
            articles.let { adapter.submitList(it) }
        }

        return view
    }

}