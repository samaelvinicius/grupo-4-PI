package com.projetointegrador.grupo04.moviesseries.viewmodel

import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.*
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.moviesseries.model.MovieDetailModel
import com.projetointegrador.grupo04.moviesseries.model.MovieModel
import com.projetointegrador.grupo04.moviesseries.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.GET

class MovieDetailViewModel(private val repository: MovieRepository): ViewModel() {

    fun getMovieDetail(movieId: Int?) = liveData(Dispatchers.IO) {
        // Obtem dados da API
        val response = repository.getMovieDetail(movieId)
        emit(response)
    }

    class MovieDetailViewModelFactory(
        private val repository: MovieRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieDetailViewModel(repository) as T
        }
    }
}