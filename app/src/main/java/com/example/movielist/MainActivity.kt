package com.example.movielist

import okhttp3.*
import android.os.Bundle
import java.io.IOException
import com.google.gson.GsonBuilder
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val apiKey = "3b547d87b9704c60b0e0c03ae26e5cd4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MovieTypes.LATEST.fetchMovies()
    }

    private fun MovieTypes.fetchMovies() {
        val type = when(this) {
            MovieTypes.LATEST -> "latest"
            MovieTypes.UPCOMING -> "upcoming"
            MovieTypes.NOW_PLAYING -> "now_playing"
            MovieTypes.TOP_RATED -> "top_rated"
            MovieTypes.POPULAR -> "popular"
        }

        val url = "https://api.themoviedb.org/3/movie/$type?api_key=$apiKey"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        if (type == "upcoming" || type == "now_playing") {
            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    val itemType = object : TypeToken<MovieResponse>() {}.type
                    val gson = GsonBuilder().create().fromJson<MovieResponse>(body, itemType)

                    runOnUiThread {
                        // This is where your recyclerView adapter should be
                        // Example: recyclerView.adapter = MainAdapter(gson.results)
                        // gson.results return an array of Upcoming Movies
                        // View here to see how the API response looks like https://api.themoviedb.org/3/movie/upcoming?api_key=3b547d87b9704c60b0e0c03ae26e5cd4
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    println("Failed to execute request")
                }
            })
        } else if (type == "top_rated" || type == "popular") {
            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    val itemType = object : TypeToken<TopRatedMovies>() {}.type
                    val gson = GsonBuilder().create().fromJson<TopRatedMovies>(body, itemType)

                    runOnUiThread {
                        // This is where your recyclerView adapter should be
                        // Example: recyclerView.adapter = MainAdapter(gson.results)
                        // gson.results return an array of Upcoming Movies
                        // View here to see how the API response looks like https://api.themoviedb.org/3/movie/upcoming?api_key=3b547d87b9704c60b0e0c03ae26e5cd4
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    println("Failed to execute request")
                }
            })
        } else if (type == "latest") {
            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    val itemType = object : TypeToken<LatestMovies>() {}.type
                    val gson = GsonBuilder().create().fromJson<LatestMovies>(body, itemType)

                    runOnUiThread {
                        // This is where your recyclerView adapter should be
                        // Example: recyclerView.adapter = MainAdapter(gson.results)
                        // gson.results return an array of Upcoming Movies
                        // View here to see how the API response looks like https://api.themoviedb.org/3/movie/upcoming?api_key=3b547d87b9704c60b0e0c03ae26e5cd4
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    println("Failed to execute request")
                }
            })
        }
    }
}
