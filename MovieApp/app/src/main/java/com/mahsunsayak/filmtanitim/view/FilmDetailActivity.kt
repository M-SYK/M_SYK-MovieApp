package com.mahsunsayak.filmtanitim.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mahsunsayak.filmtanitim.databinding.ActivityFilmDetailBinding
import com.mahsunsayak.filmtanitim.model.Film

class FilmDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilmDetailBinding
    private val filmFragmanLinkleri = getFilmFragmanLinkleri()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent'ten film nesnesini al
        val film = intent.getSerializableExtra("film") as Film

        // Film detaylarını göstermek için gerekli UI bileşenlerini güncelleyin
        binding.titleTextView.text = film.title
        binding.originalLanguageTextView.text = "Dil: ${film.original_language}"
        binding.voteAverageTextView.text = "IMDB: ${film.vote_average}"
        binding.descriptionTextView.text = film.overview

        // Film posterini Glide kullanarak yükle
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${film.poster_path}")
            .into(binding.imageView)

        binding.watchMovieButton.setOnClickListener {
            val intent = Intent(this, WatchMovieActivity::class.java)
            intent.putExtra("movieUrl", "https://www.fullhdfilmizlesene.pw/") //Site rastgele seçilmiştir. Amaç WebView kullanarak veriyi aktivite de göstermek.
            startActivity(intent)
        }

        binding.watchTrailerButton.setOnClickListener {
            val film = intent.getSerializableExtra("film") as Film
            val trailerUrl = filmFragmanLinkleri[film.title] ?: ""

            val intent = Intent(this, TrailerActivity::class.java)
            intent.putExtra("trailerUrl", trailerUrl)
            startActivity(intent)
        }
    }

    private fun getFilmFragmanLinkleri(): Map<String, String> {
        return mapOf(
            "Barbie" to "https://www.youtube.com/embed/NlJm3A0gj0Q",
            "Fast X" to "https://www.youtube.com/embed/RnJbSIqOQCE",
            "Meg 2: The Trench" to "https://www.youtube.com/embed/FL0zjq1q8HI",
            "The Nun II" to "https://www.youtube.com/embed/W6ESi2OLWeE",
            "The Flash" to "https://www.youtube.com/embed/tmK48rmkpUw",
            "Indiana Jones and the Dial of Destiny" to "https://www.youtube.com/embed/Jju7wgU62Dk",
            "Elemental" to "https://www.youtube.com/embed/xZxoOju6BK8",
            "Strays" to "https://www.youtube.com/embed/26Xq6_g2r6Q",
            "Sound of Freedom" to "https://www.youtube.com/embed/QG-3u3RZsSA",
            "Teenage Mutant Ninja Turtles: Mutant Mayhem" to "https://www.youtube.com/embed/fb2TQHKkEhQ",
            "I Am Rage" to "https://www.youtube.com/embed/jU5krtM2rNk",
            "No Hard Feelings" to "https://www.youtube.com/embed/qxwqZ_alg3U",
            "The Godfather" to "https://www.youtube.com/embed/76h6LCVk_Gk",
            "The Shawshank Redemption" to "https://www.youtube.com/embed/PLl99DlL6b4",
            "The Godfather Part II" to "https://www.youtube.com/embed/9O1Iy9od7-A",
            "Schindler's List" to "https://www.youtube.com/embed/mxphAlJID9U",
            "Dilwale Dulhania Le Jayenge" to "https://www.youtube.com/embed/oIZ4U21DRlM",
            "12 Angry Men" to "https://www.youtube.com/embed/r9Sq3jYK1Vw",
            "Parasite" to "https://www.youtube.com/embed/Xey1oYCWbV4",
            "The Dark Knight" to "https://www.youtube.com/embed/xl2NZ0Bt7yQ",
            "The Green Mile" to "https://www.youtube.com/embed/hfa5F-kRTq4",
            "Pulp Fiction" to "https://www.youtube.com/embed/MNy5tzNXJTA",
            "Forrest Gump" to "https://www.youtube.com/embed/bLvqoHBptjg",
            "The Lord of the Rings: The Return of the King" to "https://www.youtube.com/embed/r5X-hFf6Bwo",
            "The Good, The Bad, and the Ugly" to "https://www.youtube.com/embed/IFNUGzCOQoI",
            "Goodfellas" to "https://www.youtube.com/embed/2ilzidi_J8Q",
            "Cinema Paradiso" to "https://www.youtube.com/embed/JMyVSD6OvO8",
            "Life Is Beautiful" to "https://www.youtube.com/embed/8CTjcVr9Iao",
            "Grave of the Fireflies" to "https://www.youtube.com/embed/4vPeTSRd580",
            "After Everything" to "https://www.youtube.com/embed/Ym8tWY_GLgE",
            "The Last Voyage of the Demeter" to "https://www.youtube.com/embed/6FgUUO9Ztd0",
            "John Wick Chapter 4" to "https://www.youtube.com/embed/EShMQOvpvVg",
            "The Equalizer 3" to "https://www.youtube.com/embed/Q6-VlJmWC4c",
            "A Haunting in Venice" to "https://www.youtube.com/embed/23H9-c5axuk",
            "Retribution" to "https://www.youtube.com/embed/mZvz-Sde2xg",
            "To Catch a Killer" to "https://www.youtube.com/embed/CBS-QeZg-mY"
        )
    }

}

