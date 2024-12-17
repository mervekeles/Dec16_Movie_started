package com.example.dec16_movie_started.data

import com.squareup.moshi.Json

data class ResultResponse(val results : List<Movie>)


data class Movie(
    val id: Int = 0,
    @Json(name = "original_title") val title: String?,
    val overview: String?,
    @Json(name = "poster_path")  val posterPath:String)
