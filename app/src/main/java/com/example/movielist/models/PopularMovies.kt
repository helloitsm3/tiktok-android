package com.example.movielist.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularMovies(
    @SerializedName("id")
    val movie_id: String ?,

    @SerializedName("title")
    val movie_title: String?,

    @SerializedName("poster_path")
    val movie_poster: String?,

    @SerializedName("release_date")
    val movie_release_date: String?,

    @SerializedName("vote_average")
    val movie_vote: String?,

    @SerializedName("overview")
    val movie_overview: String?,


) : Parcelable{
}