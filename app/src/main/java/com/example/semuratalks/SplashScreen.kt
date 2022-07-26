package com.example.semuratalks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.semuratalks.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}