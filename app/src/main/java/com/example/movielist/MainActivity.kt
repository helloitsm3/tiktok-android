package com.example.movielist

import android.util.Log
import android.os.Bundle
import retrofit2.Callback
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.movielist.models.PopularMovies
import com.example.movielist.models.MovieResponse
import com.example.movielist.services.MovieApiService
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.services.MovieApiInterface
import kotlinx.android.synthetic.main.activity_main.*

import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val filename = "myfile"
    private val apiKey = "15df731b1aeaea1d78e2fd0bf2011e2a"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        favourite_page_button.setOnClickListener{
            openFavouriteActivity()
        }
        rv_movies_list.layoutManager = LinearLayoutManager(this)
        rv_movies_list.setHasFixedSize(true)
        fetchMovieData { popularMovies: List<PopularMovies> ->
            rv_movies_list.adapter = MovieAdapter(popularMovies)
        }

    }

    fun openDetailActivity(popularMovies: PopularMovies) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie", popularMovies)
        startActivity(intent)
    }
    fun openFavouriteActivity() {
        val intent = Intent(this, FavouriteActivity::class.java)
        startActivity(intent)
    }


private fun fetchMovieData(callback: (List<PopularMovies>) -> Unit) {
    val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
    apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}

        override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
            return callback(response.body()!!.popularMovies)
        }
    })
}}