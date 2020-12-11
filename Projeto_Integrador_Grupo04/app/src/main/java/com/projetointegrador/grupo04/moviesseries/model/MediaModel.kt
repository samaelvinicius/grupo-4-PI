package com.projetointegrador.grupo04.moviesseries.model

import com.google.gson.annotations.SerializedName

data class MediaModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("name") val name: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("poster_path") val posterPath: String
)