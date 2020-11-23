package com.projetointegrador.grupo04.explore.model

import com.google.gson.annotations.SerializedName

data class PersonagemModel(
    val id: Int,
    @SerializedName("title")
    val nome: String,
    @SerializedName("poster_path")
    val posterPath: String
)