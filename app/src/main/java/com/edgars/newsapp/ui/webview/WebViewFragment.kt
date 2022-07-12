package com.edgars.newsapp.ui.webview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.edgars.newsapp.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {

    private lateinit var binding: FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)

        webViewClient()

        return binding.root
    }

    private fun webViewClient() {
        binding.webView.webViewClient = WebViewClient()
        binding.webView.apply {
            loadUrl("https://apple.com")
        }
    }
}