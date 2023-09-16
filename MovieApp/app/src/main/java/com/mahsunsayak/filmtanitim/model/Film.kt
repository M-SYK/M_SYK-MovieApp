package com.mahsunsayak.filmtanitim.model

import java.io.Serializable

data class Film(
    val title: String,           //Film başlığı
    val original_language: String, //Orijinal dil
    val vote_average: Double,    //Ortalama oy puanı
    val overview: String,        //Film hakkında genel bilgi
    val backdrop_path: String,   //Arkaplan resmi yolu
    val poster_path: String,     //Poster resmi yolu
    val release_date: String     //Yayınlanma tarihi
): Serializable {

}
