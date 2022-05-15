package com.example.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.movielist.models.PopularMovies
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
    private var isFav = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        var favPref = prefs.favPref
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

            isFav = favPref.contains(movie.movie_id);
            if(isFav){
                toggle_favourite_btn.setImageResource(android.R.drawable.btn_star_big_on)
            }
            else{
                toggle_favourite_btn.setImageResource(android.R.drawable.btn_star_big_off)
            }

            toggle_favourite_btn.setOnClickListener{
                Log.v("tag", "TEST")
                if(isFav){
                    isFav = false
                    toggle_favourite_btn.setImageResource(android.R.drawable.btn_star_big_off)
                    favPref = HashSet(prefs.favPref)
                    favPref.remove(movie.movie_id)
                    prefs.favPref = favPref

                }
                else{
                    isFav = true
                    toggle_favourite_btn.setImageResource(android.R.drawable.btn_star_big_on)
                    favPref = HashSet(prefs.favPref)
                    movie.movie_id?.let { favPref.add(it) }

                    prefs.favPref = favPref
                    Log.d("tag", prefs.favPref.toString())

                }
            }
        }

    }
}