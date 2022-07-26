package com.example.semuratalks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.semuratalks.databinding.ActivitySearchNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val getUrl = intent.getStringExtra("URL")!!
        Log.e("TAG", "onCreate: $getUrl", )
        binding.idwebviewdetail.loadUrl(getUrl)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}