package com.projetointegrador.grupo04.explore.repository

class PersonagemRepository {
    private val client = PersonagemEndpoint.endpoint

    suspend fun obterLista() = client.obterLista()
}
