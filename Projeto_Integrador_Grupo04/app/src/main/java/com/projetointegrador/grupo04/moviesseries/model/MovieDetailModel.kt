package com.projetointegrador.grupo04.moviesseries.model

import com.google.gson.annotations.SerializedName

data class MovieDetailModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("budget") val budget: Int,
    @SerializedName("revenue") val revenue: Int,
    val genres: List<GenreModel>
)