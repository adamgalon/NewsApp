package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.ui.NewsActivity
import com.example.newsapp.ui.NewsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : Fragment(R.layout.fragment_article) {
    lateinit var viewModel: NewsViewModel
    private val args: ArticleFragmentArgs by navArgs()
    lateinit var webView: WebView
    lateinit var fab: FloatingActionButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.webView)
        viewModel = (activity as NewsActivity).viewModel
        fab = view.findViewById(R.id.fab)
        val article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article saved!", Snackbar.LENGTH_SHORT).show()

        }
    }
}