package com.example.movielist

import okhttp3.*
import android.util.Log
import android.os.Bundle
import retrofit2.Callback
import java.io.IOException
import android.content.Context
import android.content.Intent
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
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

    // save to local disk(currently overwrites the whole file)
    private fun save(){
        val context = this.applicationContext;
        val files: Array<String> = context.fileList()

        Log.i("fileList", files.count().toString() )

        val fileContents = "Overriden"
        this.applicationContext.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }
    }
    // read from local disk(currently only has one file
    private fun read(){
        val context = this.applicationContext;
        context.openFileInput(filename).bufferedReader().useLines { lines ->
            val i = lines.fold("") { some, text ->
                "$some\n$text"
            }
            Log.i("fileList", i );
        }

        Log.i("fileList", context.fileList().count().toString() );
    }

//    private fun MovieTypes.fetchMovies() {
//        val type = when(this) {
//            MovieTypes.LATEST -> "latest"
//            MovieTypes.UPCOMING -> "upcoming"
//            MovieTypes.NOW_PLAYING -> "now_playing"
//            MovieTypes.TOP_RATED -> "top_rated"
//            MovieTypes.POPULAR -> "popular"
//        }
//
//        val url = "https://api.themoviedb.org/3/movie/$type?api_key=$apiKey"
//        val request = Request.Builder().url(url).build()
//        val client = OkHttpClient()
//
//        if (type == "upcoming" || type == "now_playing") {
//            client.newCall(request).enqueue(object : Callback {
//                fun onResponse(call: Call, response: Response) {
//                    val body = response.body?.string()
//                    val itemType = object : TypeToken<MovieResponse2>() {}.type
//                    val gson = GsonBuilder().create().fromJson<MovieResponse2>(body, itemType)
//
//                    runOnUiThread {
//                        // This is where your recyclerView adapter should be
//                        // Example: recyclerView.adapter = MainAdapter(gson.results)
//                        // gson.results return an array of Upcoming Movies
//                        // View here to see how the API response looks like https://api.themoviedb.org/3/movie/upcoming?api_key=3b547d87b9704c60b0e0c03ae26e5cd4
//                    }
//                }
//
//                override fun onFailure(call: Call, e: IOException) {
//                    println("Failed to execute request")
//                }
//            })
//        } else if (type == "top_rated" || type == "popular") {
//            client.newCall(request).enqueue(object : Callback {
//                fun onResponse(call: Call, response: Response) {
//                    val body = response.body?.string()
//                    val itemType = object : TypeToken<TopRatedMovies>() {}.type
//                    val gson = GsonBuilder().create().fromJson<TopRatedMovies>(body, itemType)
//
//                    runOnUiThread {
//                        // This is where your recyclerView adapter should be
//                        // Example: recyclerView.adapter = MainAdapter(gson.results)
//                        // gson.results return an array of Upcoming Movies
//                        // View here to see how the API response looks like https://api.themoviedb.org/3/movie/upcoming?api_key=3b547d87b9704c60b0e0c03ae26e5cd4
//                    }
//                }
//
//                fun onFailure(call: Call, e: IOException) {
//                    println("Failed to execute request")
//                }
//            })
//        } else if (type == "latest") {
//            client.newCall(request).enqueue(object : Callback {
//                fun onResponse(call: Call, response: Response) {
//                    val body = response.body?.string()
//                    val itemType = object : TypeToken<LatestMovies>() {}.type
//                    val gson = GsonBuilder().create().fromJson<LatestMovies>(body, itemType)
//
//                    runOnUiThread {
//                        // This is where your recyclerView adapter should be
//                        // Example: recyclerView.adapter = MainAdapter(gson.results)
//                        // gson.results return an array of Upcoming Movies
//                        // View here to see how the API response looks like https://api.themoviedb.org/3/movie/upcoming?api_key=3b547d87b9704c60b0e0c03ae26e5cd4
//                    }
//                }
//
//                fun onFailure(call: Call, e: IOException) {
//                    println("Failed to execute request")
//                }
//            })
//        }
//    }
}

private fun fetchMovieData(callback: (List<PopularMovies>) -> Unit) {
    val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
    apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
        override fun onFailure(call: Call<MovieResponse>, t: Throwable) { }

        override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
            return callback(response.body()!!.popularMovies)
        }
    })
}