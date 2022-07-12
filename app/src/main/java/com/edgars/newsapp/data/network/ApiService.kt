package com.edgars.newsapp.data.network

import com.edgars.newsapp.data.vo.News
import retrofit2.Call
import retrofit2.http.GET

private const val API_KEY = "8989615aa6ca474fa80d812479630582"

interface ApiService {

    @GET("everything?q=apple&apiKey=${API_KEY}")
    suspend fun getNews(): News

    // Not used
    @GET("everything?q=apple&apiKey=${API_KEY}")
    fun getTotalResults(): Call<News>

    @GET("everything?q=apple&apiKey=${API_KEY}")
    fun getAppleNewsRetrofit(): Call<News>
}