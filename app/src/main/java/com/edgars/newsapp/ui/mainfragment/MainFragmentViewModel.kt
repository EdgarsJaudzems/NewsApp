package com.edgars.newsapp.ui.mainfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edgars.newsapp.data.network.ScreenState
import com.edgars.newsapp.data.network.ApiClient
import com.edgars.newsapp.data.network.ApiService
import com.edgars.newsapp.data.vo.Article
import com.edgars.newsapp.data.vo.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainFragmentViewModel : ViewModel() {

    private val _articles = MutableLiveData<ScreenState<List<Article>>>()
    val articles: LiveData<ScreenState<List<Article>>> = _articles

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        _articles.postValue(ScreenState.Loading(null))

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiClient = ApiClient.getRetrofitInstance().create(ApiService::class.java)
                val client = apiClient.getNews()
                _articles.postValue(ScreenState.Success(client.articles))
            } catch (e: Exception) {
                _articles.postValue(ScreenState.Error(e.message!!,null))
            }
        }
    }

    private fun fetchArticlesRetrofit() {
        val apiClient = ApiClient.getRetrofitInstance().create(ApiService::class.java)
        val client = apiClient.getAppleNewsRetrofit()
        _articles.value = ScreenState.Loading(null)

        client.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    _articles.value = ScreenState.Success(response.body()?.articles)
                } else {
                    _articles.value = ScreenState.Error(response.code().toString(),null)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Failure", t.message.toString())
                _articles.value = ScreenState.Error(t.message.toString(),null)
            }
        })
    }
}