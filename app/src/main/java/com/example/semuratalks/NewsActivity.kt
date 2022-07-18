package com.example.semuratalks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.semuratalks.adapter.NewsAdapter
import com.example.semuratalks.api.EndpointsItem
import com.example.semuratalks.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getDataNews = intent.getParcelableExtra<EndpointsItem>("datanews") as EndpointsItem
        val kategori = getDataNews.paths

        binding.idrvCategorynews.apply {
            adapter = NewsAdapter(kategori)
        }
    }
}