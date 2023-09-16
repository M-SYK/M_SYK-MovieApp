package com.mahsunsayak.filmtanitim.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mahsunsayak.filmtanitim.databinding.ActivityTrailerBinding

class TrailerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrailerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrailerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Toolbar'ı ayarla
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //Geri tuşunu göster
        supportActionBar?.title = "Fragmanı İzle"  //Toolbar başlığını güncelle

        val trailerUrl = intent.getStringExtra("trailerUrl") //Intent'ten trailUrl'i al
        showVideo(trailerUrl)
    }

    // WebView'da videoyu göster
    private fun showVideo(videoUrl: String?) {
        val webView = binding.webView
        val html = "<iframe width=\"100%\" height=\"100%\" src=\"$videoUrl\" frameborder=\"5\" allowfullscreen></iframe>"
        webView.settings.javaScriptEnabled = true
        webView.loadData(html, "text/html", "utf-8")
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