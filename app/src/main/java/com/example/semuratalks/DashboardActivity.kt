package com.example.semuratalks

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.semuratalks.adapter.DashboardAdapter
import com.example.semuratalks.adapter.SearchAdapter
import com.example.semuratalks.api.ApiConfig
import com.example.semuratalks.api.Articles
import com.example.semuratalks.api.ArticlesItem
import com.example.semuratalks.databinding.ActivityMainBinding
import com.example.semuratalks.model.DashboardModel
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showPlatform()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                showSearch(query)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return true
    }

    fun showPlatform() {
        val berita = arrayListOf(
            "Antara",
            "Cnbc",
            "Cnn",
            "Merdeka",
            "Okezone",
            "Republika",
            "Sindenews",
            "Suara",
            "Tempo",
            "Tribun"
        )

        val gambarBerita = arrayListOf(
            "https://www.antaranews.com/img/antaranews.com.png",
            "https://cdn.cnbcindonesia.com/cnbc/images/logo_hitam.png",
            "https://cdn.cnnindonesia.com/cnnid/images/logo_cnn_fav.png",
            "https://cdns.klimg.com/merdeka.com/media/i/a/logo.png",
            "https://cdn.okezone.com/underwood/revamp/2020/img/xokezone2020.png.pagespeed.ic.o1H1D1nrFk.png",
            "https://static.republika.co.id/files/images/logo.png",
            "https://sm.sindonews.net/mobile/2016/images/sindonews-480.png",
            "https://assets.suara.com/desktop/images/new-images/logo_suara.png",
            "https://www.tempo.co/images/logo-tempo.png",
            "https://cdn-1.tstatic.net/img/logo/tribun/png/tribunnews.png"
        )

        val dataLengkap = arrayListOf<DashboardModel>()
        for (item in berita.indices){
            dataLengkap.add(DashboardModel(gambarBerita[item], berita[item]))
        }

        binding.idrvDashboard.apply {
            adapter = DashboardAdapter(dataLengkap)
            layoutManager = GridLayoutManager(this@DashboardActivity, 2)
        }
    }

    fun showSearch(value: String) {
        val retrofit = ApiConfig.getApiService().getAllplatform(value)
        retrofit.enqueue(object : Callback<Articles> {
            override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@DashboardActivity, value, Toast.LENGTH_SHORT).show()
                    val responseBody = response.body()
                    showSearchRv(responseBody!!)
                }
            }

            override fun onFailure(call: Call<Articles>, t: Throwable) {
                Snackbar.make(this@DashboardActivity.binding.root, "Kesalahan ${t.message}", Snackbar.LENGTH_SHORT).show()
            }

        })
    }

    fun showSearchRv(response: Articles) {
        val data = arrayListOf<ArticlesItem>()

        for (item in response.articlesItem) {
            data.add(ArticlesItem(item.sourceItem, item.title, item.url, item.urlToImage))
        }
        binding.idrvDashboard.apply {
            adapter = SearchAdapter(this@DashboardActivity, data)
            layoutManager = LinearLayoutManager(this@DashboardActivity)
        }
    }

}