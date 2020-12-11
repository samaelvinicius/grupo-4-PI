package com.projetointegrador.grupo04.moviesseries.model

import com.google.gson.annotations.SerializedName

data class SerieDetailModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    val genres: List<GenreModel>
)