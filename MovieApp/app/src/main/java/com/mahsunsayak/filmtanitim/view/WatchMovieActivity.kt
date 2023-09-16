package com.mahsunsayak.filmtanitim.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.mahsunsayak.filmtanitim.databinding.ActivityWatchMovieBinding

class WatchMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWatchMovieBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Toolbar'ı ayarla
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //Geri tuşunu göster
        supportActionBar?.title = "Film İzle"  //Toolbar başlığını güncelle

        val movieUrl = intent.getStringExtra("movieUrl") ?: "" //Intent'ten movieUrl'i al

        // WebView ayarları
        binding.webView.settings.javaScriptEnabled = true //JavaScript'i etkinleştirir
        binding.webView.webChromeClient = WebChromeClient() // WebChromeClient, JavaScript uyarıları ve diğer olayları yönetir.
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString()) //Yeni bir URL yüklenmesi gerektiğinde, WebView'e yükleme isteği gönderir.
                return true
            }
        }
        // WebView'e videoyu yükle
        binding.webView.loadUrl(movieUrl)
    }

    //Geri tuşuna basıldığında
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
