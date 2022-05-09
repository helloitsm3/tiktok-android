package com.example.movielist

class MovieResponse(
    val dates: dates,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

class dates(val maximum: String, val minimum: String)

class Movie (
    val adult: Boolean,
    val backdrop_path: String,
    var genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
)

enum class MovieTypes {
    LATEST,
    NOW_PLAYING,
    UPCOMING,
    POPULAR,
    TOP_RATED
}