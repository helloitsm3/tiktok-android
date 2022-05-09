package com.example.movielist

class LatestMovies(
    val adult: Boolean,
    val backdrop_path: String?,
    val belongs_to_collection: String?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Int,
    val poster_path: String?,
    val production_companies: List<String>,
    val production_countries: List<String>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<String>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Int,
    val vote_count: Int
)

class Genre (
    val id: Int,
    val name: String
)