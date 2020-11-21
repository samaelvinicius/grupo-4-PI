package com.projetointegrador.grupo04.movies_series.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projetointegrador.grupo04.movies_series.model.MovieModel
import com.projetointegrador.grupo04.movies_series.repository.MovieRepository

class MovieListViewModel(private val repository: MovieRepository): ViewModel() {
    val movieList = MutableLiveData<List<MovieModel>>()

    fun getList() {
        repository.getList {
            movieList.value = it
        }
    }

    class ListRestaurantListViewModelFactory(
        private val repository: MovieRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieListViewModel(repository) as T
        }
    }
}