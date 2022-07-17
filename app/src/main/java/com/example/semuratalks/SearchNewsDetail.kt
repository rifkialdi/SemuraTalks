package com.example.semuratalks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.semuratalks.databinding.ActivitySearchNewsDetailBinding

class SearchNewsDetail : AppCompatActivity() {

    private lateinit var binding: ActivitySearchNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getUrl = intent.getStringExtra("URL")!!
        binding.idwebviewdetail.loadUrl(getUrl)
    }
}