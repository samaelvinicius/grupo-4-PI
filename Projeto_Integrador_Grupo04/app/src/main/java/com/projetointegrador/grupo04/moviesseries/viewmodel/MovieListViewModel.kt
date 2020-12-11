package com.projetointegrador.grupo04.moviesseries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.projetointegrador.grupo04.moviesseries.model.MovieModel
import com.projetointegrador.grupo04.moviesseries.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieListViewModel(private val repository: MovieRepository): ViewModel() {

    private var _movies: List<MovieModel> = listOf()

    fun getMovieDetail(movieId: Int?) = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getMovieDetail(movieId)
        emit(response)
    }

    fun getPopularMovies() = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getPopularMovies()

        _movies = response.results
        emit(response.results)
    }

    fun getLatestMovies() = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getLatestMovies()

        _movies = response.results
        emit(response.results)
    }

    fun getTrendingMovies() = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getTrendingMovies()

        _movies = response.results
        emit(response.results)
    }

    fun getUpcomingMovies() = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getUpcomingMovies()

        _movies = response.results
        emit(response.results)
    }

    fun getTopRatedMovies() = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getTopRatedMovies()

        _movies = response.results
        emit(response.results)
    }

    fun getNowPlayingMovies() = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getNowPlayingMovies()

        _movies = response.results
        emit(response.results)
    }

    class MovieListViewModelFactory(
        private val repository: MovieRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieListViewModel(repository) as T
        }
    }
}