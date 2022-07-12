package com.edgars.newsapp.ui.detailsfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.edgars.newsapp.R
import com.edgars.newsapp.databinding.FragmentDetailsWebViewBinding
import com.edgars.newsapp.databinding.FragmentWebViewBinding
import com.google.android.material.snackbar.Snackbar
import java.net.URI
import java.net.URL

class DetailsWebViewFragment : Fragment() {

    companion object {
        val URL = "url"
    }

    private lateinit var binding: FragmentDetailsWebViewBinding

    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(URL).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsWebViewBinding.inflate(inflater, container, false)

        webViewClient()

        return binding.root
    }

    private fun webViewClient() {
        binding.webView.webViewClient = WebViewClient()
        val uri: String = url
        binding.webView.apply {
            if (uri.isNotEmpty()) {
                loadUrl(uri)
            } else {
                Snackbar.make(this, "URL does not exist", Snackbar.LENGTH_LONG).show()
            }
        }
    }

}