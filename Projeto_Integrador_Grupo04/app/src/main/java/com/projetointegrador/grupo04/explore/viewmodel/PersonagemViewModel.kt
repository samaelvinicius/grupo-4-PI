package com.projetointegrador.grupo04.explore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.projetointegrador.grupo04.explore.model.PersonagemModel
import com.projetointegrador.grupo04.explore.repository.PersonagemRepository
import kotlinx.coroutines.Dispatchers

class PersonagemViewModel(
    private val repository: PersonagemRepository
): ViewModel() {

    private var _personagens: List<PersonagemModel> = listOf()

    private var _page: Int = 1
    private var _total: Int = 0

    fun obterLista() = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.obterLista()

        _personagens = response.results
        //_total = response.info.paginas

        emit(response.results)
    }

    class PersonagemViewModelFactory(
        private val repository: PersonagemRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PersonagemViewModel(repository) as T
        }
    }
}