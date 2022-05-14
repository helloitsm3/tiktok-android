package com.example.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielist.models.PopularMovies
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(
    private val popularMovies: List<PopularMovies>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        fun bindMovie(popularMovies: PopularMovies) {
            itemView.movie_title.text = popularMovies.movie_title
            itemView.movie_release_date.text = popularMovies.movie_release_date
            Glide.with(itemView).load(IMAGE_BASE + popularMovies.movie_poster).into(itemView.movie_poster)
            itemView.vote.text = popularMovies.movie_vote

            itemView.setOnClickListener {
                if (itemView.context is MainActivity) {
                    (itemView.context as MainActivity).openDetailActivity(popularMovies)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )

    override fun getItemCount(): Int = popularMovies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(popularMovies.get(position))
    }
}