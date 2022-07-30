package com.example.semuratalks

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.semuratalks.adapter.DashboardAdapter
import com.example.semuratalks.adapter.SearchAdapter
import com.example.semuratalks.api.*
import com.example.semuratalks.databinding.ActivityMainBinding
import com.example.semuratalks.model.DashboardModel
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var data: ArrayList<EndpointsItem>
    var validateBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = arrayListOf()
        dataPlatformBerita()
    }

//    Untuk menampilkan UI search bar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.idtvTidakada.visibility = View.GONE
                binding.idprogressbar.visibility = View.VISIBLE
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

//    Untuk menampilkan data json dari search bar
    fun showSearch(value: String) {
        val retrofit = ApiConfig.getApiService("https://newsapi.org/").getAllplatform(value)
        retrofit.enqueue(object : Callback<Articles> {
            override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@DashboardActivity, value, Toast.LENGTH_SHORT).show()
                    val responseBody = response.body()
                    showSearchRv(responseBody!!)
                    validateBack = true
                    binding.idprogressbar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<Articles>, t: Throwable) {
                Snackbar.make(this@DashboardActivity.binding.root, "Kesalahan ${t.message}", Snackbar.LENGTH_SHORT).show()
            }
        })
    }
    
//    Untuk membuat recyclerview dari data json Search
    fun showSearchRv(response: Articles) {
        val dataSearh = arrayListOf<ArticlesItem>()

        for (item in response.articlesItem) {
            dataSearh.add(ArticlesItem(item.sourceItem, item.title, item.url, item.urlToImage))
        }

        if (dataSearh.size != 0) {
            binding.idrvDashboard.apply {
                adapter = SearchAdapter(this@DashboardActivity, dataSearh)
                layoutManager = LinearLayoutManager(this@DashboardActivity)
            }
        } else {
            binding.idrvDashboard.apply {
                adapter = SearchAdapter(this@DashboardActivity, arrayListOf<ArticlesItem>())
                layoutManager = LinearLayoutManager(this@DashboardActivity)
            }
            binding.idtvTidakada.visibility = View.VISIBLE
        }
    }

//    Untuk menampilkan data json dari pilihan sumber berita
    fun dataPlatformBerita() {
        val retrofit = ApiConfig.getApiService("https://api-berita-indonesia.vercel.app/").getDataPlatform()
        retrofit.enqueue(object : Callback<ResponseNews> {
            override fun onResponse(
                call: Call<ResponseNews>,
                response: Response<ResponseNews>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()!!

                    for (index in 0..11) {
                        if (index !in 3..4) {
                            data.add(responseBody.endpoints[index])
                        } else {
                            false
                        }
                    }
                    showPlatform()
                }
            }

            override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                Snackbar.make(this@DashboardActivity.binding.root, "Kesalahan ${t.message}", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    //    Untuk menampilkan pilihan sumber berita
    fun showPlatform() {
        val berita = arrayListOf(
            "ANTARA NEWS",
            "CNBC",
            "CNN Indonesia",
            "merdeka.com",
            "okezone.com",
            "REPUBLIKA.co.id",
            "SINDONEWS.com",
            "suara.com",
            "tempo.co",
            "Tribunnews.com"
        )

        val gambarBerita = arrayListOf(
            "https://www.antaranews.com/img/antaranews.com.png",
            "https://firebasestorage.googleapis.com/v0/b/logo-1e078.appspot.com/o/cnbc.png?alt=media&token=2b05cbf1-022f-4411-bab5-beb040818cbb",
            "https://cdn.cnnindonesia.com/cnnid/images/logo_cnn_fav.png",
            "https://cdns.klimg.com/merdeka.com/media/i/a/logo.png",
            "https://cdn.okezone.com/underwood/revamp/2020/img/xokezone2020.png.pagespeed.ic.o1H1D1nrFk.png",
            "https://static.republika.co.id/files/images/logo.png",
            "https://sm.sindonews.net/mobile/2016/images/sindonews-480.png",
            "https://assets.suara.com/desktop/images/new-images/logo_suara.png",
            "https://firebasestorage.googleapis.com/v0/b/logo-1e078.appspot.com/o/tempo.png?alt=media&token=54ae5635-d8ba-4367-b57f-f0efe87f409f",
            "https://cdn-1.tstatic.net/img/logo/tribun/png/tribunnews.png"
        )

        val dataLengkap = arrayListOf<DashboardModel>()
        for (item in berita.indices){
            dataLengkap.add(DashboardModel(gambarBerita[item], berita[item]))
        }

        binding.idrvDashboard.apply {
            adapter = DashboardAdapter(dataLengkap, data)
            layoutManager = GridLayoutManager(this@DashboardActivity, 2)
            binding.idprogressbar.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        if (validateBack) {
            binding.idprogressbar.visibility = View.VISIBLE
            binding.idtvTidakada.visibility = View.GONE
            dataPlatformBerita()
            validateBack = false
        } else {
            super.onBackPressed()
        }
    }

}