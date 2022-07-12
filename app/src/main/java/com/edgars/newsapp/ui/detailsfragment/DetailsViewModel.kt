package com.edgars.newsapp.ui.detailsfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edgars.newsapp.data.network.ScreenState
import com.edgars.newsapp.data.vo.Article

class DetailsViewModel : ViewModel() {

    private val _singleArticle = MutableLiveData<ScreenState<List<Article>>>()
    val singleArticle: LiveData<ScreenState<List<Article>>> = _singleArticle

    init {

    }
}