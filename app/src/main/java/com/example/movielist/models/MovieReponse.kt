package com.example.movielist.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    @SerializedName("results")
    val popularMovies : List<PopularMovies>

) : Parcelable {
    constructor() : this(mutableListOf())
}