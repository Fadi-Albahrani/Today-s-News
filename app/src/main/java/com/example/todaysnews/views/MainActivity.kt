package com.example.todaysnews.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todaysnews.adapters.ViewPagerAdapter
import com.example.todaysnews.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

val tabs = arrayOf(
    "Latest News",
    "Offline Mode"
)

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabs[position]
        }.attach()

    }
}