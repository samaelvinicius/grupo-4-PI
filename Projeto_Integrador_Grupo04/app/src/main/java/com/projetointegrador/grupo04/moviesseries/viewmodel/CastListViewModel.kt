package com.projetointegrador.grupo04.moviesseries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.projetointegrador.grupo04.moviesseries.model.CastModel
import com.projetointegrador.grupo04.moviesseries.model.MovieModel
import com.projetointegrador.grupo04.moviesseries.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class CastListViewModel(private val repository: MovieRepository): ViewModel() {

    private var _cast: List<CastModel> = listOf()

    fun getMovieCast(movieId: Int?) = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getMovieCast(movieId)

        _cast = response!!.cast
        emit(response.cast)
    }

    class CastListViewModelFactory(
        private val repository: MovieRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CastListViewModel(repository) as T
        }
    }
}