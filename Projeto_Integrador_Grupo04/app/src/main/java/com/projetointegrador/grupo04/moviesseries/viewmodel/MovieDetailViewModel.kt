package com.projetointegrador.grupo04.moviesseries.viewmodel

import androidx.lifecycle.*
import com.projetointegrador.grupo04.moviesseries.repository.MediaRepository
import kotlinx.coroutines.Dispatchers

class MovieDetailViewModel(private val repository: MediaRepository): ViewModel() {

    fun getMovieDetail(id: Int?) = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getMovieDetail(id)
        emit(response)
    }

    fun getSerieDetail(id: Int?) = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getSerieDetail(id)
        emit(response)
    }

    class MovieDetailViewModelFactory(
        private val repository: MediaRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieDetailViewModel(repository) as T
        }
    }
}