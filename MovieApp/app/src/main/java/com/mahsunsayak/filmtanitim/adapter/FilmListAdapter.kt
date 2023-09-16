package com.mahsunsayak.filmtanitim.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahsunsayak.filmtanitim.databinding.ItemFilmBinding
import com.mahsunsayak.filmtanitim.model.Film

class FilmListAdapter(private val filmList: List<Film>, private val onClick: (Film) -> Unit) : RecyclerView.Adapter<FilmListAdapter.FilmViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(film: Film)
    }

    inner class FilmViewHolder(val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film) {
            binding.titleTextView.text = film.title
            binding.releaseDateTextView.text = "Yayın Tarihi: ${film.release_date}"

            itemView.setOnClickListener { onClick(film) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = filmList[position]
        holder.bind(film)

        // Film öğesine tıklanıldığında, onClick işlevini çağırır.
        holder.itemView.setOnClickListener {
            onClick(film)
        }

        // Glide kullanarak posteri yükle
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500${film.backdrop_path}")
            .into(holder.binding.posterImageView)
    }

    override fun getItemCount() = filmList.size
}
