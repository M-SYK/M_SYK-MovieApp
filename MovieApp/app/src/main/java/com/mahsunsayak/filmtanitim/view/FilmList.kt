package com.mahsunsayak.filmtanitim.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mahsunsayak.filmtanitim.databinding.ActivityFilmListBinding

class FilmListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilmListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Kategori butonlarına tıklama olaylarını ayarla
        binding.popularButton.setOnClickListener {
            startActivityWithCategory("popular")
        }

        binding.nowPlayingButton.setOnClickListener {
            startActivityWithCategory("now_playing")
        }

        binding.topRatedButton.setOnClickListener {
            startActivityWithCategory("top_rated")
        }

        binding.upcomingButton.setOnClickListener {
            startActivityWithCategory("upcoming")
        }
    }

    private fun startActivityWithCategory(category: String) {
        val intent = Intent(this, CategoryFilmListActivity::class.java)
        intent.putExtra("category", category)
        startActivity(intent)
    }
}
