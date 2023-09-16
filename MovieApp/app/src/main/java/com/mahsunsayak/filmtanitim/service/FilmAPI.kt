package com.mahsunsayak.filmtanitim.service

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmAPI {

    @GET("movie/popular")
    fun getData(@Query("api_key") apiKey: String): Observable<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Observable<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") apiKey: String): Observable<MovieResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String): Observable<MovieResponse>


}