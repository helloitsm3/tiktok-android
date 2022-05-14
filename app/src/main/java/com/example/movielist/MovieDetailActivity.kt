package com.example.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.movielist.models.PopularMovies
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        if (intent != null) {
            val movie = intent.getParcelableExtra<PopularMovies>("movie")
            Glide.with(this)
                .load(IMAGE_BASE + movie!!.movie_poster)
                .into(singleMovie_poster)
            singleMovie_title.text = movie.movie_title
            singleMovie_ratings.text = movie.movie_vote
            singleMovie_description.text = movie.movie_overview
            singleMovie_releaseDate.text = movie.movie_release_date
            singleMovie_language.text = movie.original_language
        }
    }
}