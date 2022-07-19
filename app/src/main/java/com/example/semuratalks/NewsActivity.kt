package com.example.semuratalks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.semuratalks.adapter.NewsAdapter
import com.example.semuratalks.adapter.NewsCategoryAdapter
import com.example.semuratalks.api.ApiConfig
import com.example.semuratalks.api.EndpointsItem
import com.example.semuratalks.api.ResponseNews
import com.example.semuratalks.api.ResponseNewsCategory
import com.example.semuratalks.databinding.ActivityNewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getDataNews = intent.getParcelableExtra<EndpointsItem>("datanews") as EndpointsItem
        val kategori = getDataNews.paths

        supportActionBar?.title = getDataNews.name

        binding.idrvCategorynews.apply {
            adapter = NewsAdapter(kategori)
        }
        Log.e("TAG", "onCreate: ${getDataNews.name} ${getDataNews.paths[0].name}", )
        showDefaultCategoryNews(getDataNews.name, getDataNews.paths[0].name)
    }

    fun showDefaultCategoryNews(platform: String, category: String) {
        val retrofit = ApiConfig.getApiService("https://api-berita-indonesia.vercel.app").getAntaraTerbaru(platform, category)
        retrofit.enqueue(object : Callback<ResponseNewsCategory> {
            override fun onResponse(
                call: Call<ResponseNewsCategory>,
                response: Response<ResponseNewsCategory>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    Log.e("TAG", "onResponse: $responseBody", )
                    showListItemNews(responseBody)
                }
            }

            override fun onFailure(call: Call<ResponseNewsCategory>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}", )
            }
        })
    }

    fun showListItemNews(data: ResponseNewsCategory) {
        binding.idrvNews.apply {
            adapter = NewsCategoryAdapter(data.data)
            layoutManager = LinearLayoutManager(this@NewsActivity)
        }
    }


}