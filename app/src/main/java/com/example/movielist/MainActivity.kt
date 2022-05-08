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

        fetchUpcomingMovies()
    }

    private fun fetchUpcomingMovies() {
        val url = "https://api.themoviedb.org/3/movie/upcoming?api_key=$apiKey"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
//                val json = JSONObject(body)
                val itemType = object : TypeToken<UpcomingMovies>() {}.type
                val gson = GsonBuilder().create().fromJson<UpcomingMovies>(body, itemType)

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
