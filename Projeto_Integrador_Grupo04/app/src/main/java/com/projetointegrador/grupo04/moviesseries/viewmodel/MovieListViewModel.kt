package com.projetointegrador.grupo04.moviesseries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.projetointegrador.grupo04.moviesseries.model.MediaModel
import com.projetointegrador.grupo04.moviesseries.repository.MediaRepository
import kotlinx.coroutines.Dispatchers

class MovieListViewModel(private val repository: MediaRepository): ViewModel() {

    private var _media: List<MediaModel> = listOf()

    fun getMovieDetail(mediaId: Int?) = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getMovieDetail(mediaId)
        emit(response)
    }

    fun getMovieRecommendations(mediaId: Int?) = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getMovieRecommendations(mediaId)

        _media = response!!.results
        emit(response!!.results)
    }

    fun getSerieDetail(mediaId: Int?) = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getSerieDetail(mediaId)
        emit(response)
    }

    fun getPopularMovies() = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getPopularMovies()

        _media = response.results
        emit(response.results)
    }

    fun getPopularSeries() = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getPopularSeries()

        _media = response.results
        emit(response.results)
    }


    fun getTrendingMovies() = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getTrendingMovies()

        _media = response.results
        emit(response.results)
    }

    fun getTrendingSeries() = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getTrendingSeries()

        _media = response.results
        emit(response.results)
    }


    fun getTopRatedMovies() = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getTopRatedMovies()

        _media = response.results
        emit(response.results)
    }

    fun getTopRatedSeries() = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getTopRatedSeries()

        _media = response.results
        emit(response.results)
    }

    fun getNowPlayingMovies() = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getNowPlayingMovies()

        _media = response.results
        emit(response.results)
    }

    fun getSeriesOnTheAir() = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getSeriesOnTheAir()

        _media = response.results
        emit(response.results)
    }

    class MovieListViewModelFactory(
        private val repository: MediaRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieListViewModel(repository) as T
        }
    }
}