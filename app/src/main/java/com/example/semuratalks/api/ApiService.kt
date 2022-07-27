package com.example.semuratalks.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET("v2/everything?language=id&apiKey=96a6673127324ac78cf038734d996152")
    fun getAllplatform(
        @Query("q") q: String
    ): Call<Articles>

    @GET("https://api-berita-indonesia.vercel.app/")
    fun getDataPlatform(): Call<ResponseNews>

    @GET("{platform}/{category}")
    fun getAntaraTerbaru(
        @Path("platform") platform: String,
        @Path("category") category: String
    ): Call<ResponseNewsCategory>
}