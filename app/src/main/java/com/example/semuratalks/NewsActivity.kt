package com.example.semuratalks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
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

        supportActionBar?.hide()

        val getDataNews = intent.getParcelableExtra<EndpointsItem>("datanews") as EndpointsItem
        val getImage = intent.getStringExtra("gambar")

        Glide.with(this)
            .load(getImage)
            .centerInside()
            .into(binding.idimg)

        val kategori = getDataNews.paths
        binding.idrvCategorynews.apply {
            adapter = NewsAdapter(kategori, object : NewsAdapter.OnItemClickCallback {
                override fun onClick(name: String) {
                    binding.idprogressbar.visibility = View.VISIBLE
                    Toast.makeText(this@NewsActivity, name, Toast.LENGTH_SHORT).show()
                    showDefaultCategoryNews(getDataNews.name, name)
                }
            })
        }
        showDefaultCategoryNews(getDataNews.name, getDataNews.paths[0].name)

        binding.idimgBack.setOnClickListener {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
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
                    showListItemNews(responseBody)
                    binding.idprogressbar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<ResponseNewsCategory>, t: Throwable) {
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