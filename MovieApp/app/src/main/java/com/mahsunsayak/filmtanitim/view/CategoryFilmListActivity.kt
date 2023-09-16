package com.mahsunsayak.filmtanitim.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahsunsayak.filmtanitim.adapter.FilmListAdapter
import com.mahsunsayak.filmtanitim.databinding.ActivityCategoryFilmListBinding
import com.mahsunsayak.filmtanitim.model.Film
import com.mahsunsayak.filmtanitim.service.FilmAPI
import com.mahsunsayak.filmtanitim.service.MovieResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("DEPRECATION")
class CategoryFilmListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryFilmListBinding
    private var recyclerViewAdapter: FilmListAdapter? = null
    private val BASE_URL = "https://api.themoviedb.org/3/"
    private var filmModels: ArrayList<Film> = ArrayList()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryFilmListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        // Intent'ten kategori bilgisini al
        val category = intent.getStringExtra("category") ?: ""
        loadDataForCategory(category)
    }

    //Belirtilen kategoriye göre verileri yükle
    private fun loadDataForCategory(category: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //JSON verilerini nesnelere dönüştürmek için Gson converter'ı ekle.
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Retrofit ile RxJava'nın kullanılabilmesini sağlar.
            .build() //Retrofit nesnesini oluşturur.

        val filmAPI = retrofit.create(FilmAPI::class.java) //Retrofit tarafından oluşturulan API hizmeti arayüzünü kullanarak bir API nesnesi oluşturur.

        val apiKey = "ebfd9918eac5781fd9f52db91165771e"

        // Kategoriye göre API'den veri çekme işlemini gerçekleştirir.
        val observable: Observable<MovieResponse> = when (category) {
            "popular" -> filmAPI.getData(apiKey)
            "now_playing" -> filmAPI.getNowPlayingMovies(apiKey)
            "top_rated" -> filmAPI.getTopRatedMovies(apiKey)
            "upcoming" -> filmAPI.getUpcomingMovies(apiKey)
            else -> Observable.empty() // Belirtilen kategori yoksa boş bir Observable döndürür.
        }

        // RxJava kullanarak veriyi çeker, bu işlemi arkaplanda yapar ve sonuçları ana thread'e iletilmesini sağlar.
        compositeDisposable.add(
            observable
                .subscribeOn(Schedulers.io()) // Veriyi arkaplanda işlemek için IO thread'i kullanır.
                .observeOn(AndroidSchedulers.mainThread()) // Sonuçları ana thread'e ileterek UI üzerinde güncelleme yapılmasını sağlar.
                .subscribe(this::handleResponse) // API yanıtını işlemek için handleResponse fonksiyonunu çağırır.
        )
    }

    // API yanıtını işleyen fonksiyon
    private fun handleResponse(movieResponse: MovieResponse) {
        val filmList = movieResponse.results // API'den gelen film listesini alır.

        if (filmList.isNotEmpty()) {
            recyclerViewAdapter = FilmListAdapter(filmList) { film ->
                val intent = Intent(this, FilmDetailActivity::class.java)
                intent.putExtra("film", film) // Film nesnesini intent'e ekler
                startActivity(intent)
            }
            binding.recyclerView.adapter = recyclerViewAdapter // RecyclerView için oluşturulan adapter'ı ayarlar.
        } else {
            Toast.makeText(this, "Bu kategoride film bulunmamaktadır.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
