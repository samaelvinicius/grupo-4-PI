package com.projetointegrador.grupo04.moviesseries.model

import com.google.gson.annotations.SerializedName

data class CastModel(
    @SerializedName("id") val id: Int,
    @SerializedName("profile_path") val profilePath: String,
    @SerializedName("name") val name: String,
    @SerializedName("character") val character: String
)