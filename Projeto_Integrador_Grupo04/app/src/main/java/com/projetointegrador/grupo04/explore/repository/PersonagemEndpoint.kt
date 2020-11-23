package com.projetointegrador.grupo04.explore.repository

import com.projetointegrador.grupo04.data.api.NetworkUtils
import com.projetointegrador.grupo04.data.model.ResponseModel
import com.projetointegrador.grupo04.explore.model.PersonagemModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonagemEndpoint {
    @GET("movie/popular?api_key=f9218c6e4d89c1979a30e457170ca934&language=pt-BR")
    suspend fun obterLista(): ResponseModel<PersonagemModel>

    companion object {
        val endpoint: PersonagemEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(PersonagemEndpoint::class.java)
        }
    }
}