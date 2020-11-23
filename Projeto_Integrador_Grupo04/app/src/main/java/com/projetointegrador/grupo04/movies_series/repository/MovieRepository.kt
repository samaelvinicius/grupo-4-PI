package com.projetointegrador.grupo04.movies_series.repository

import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.explore.repository.PersonagemEndpoint
import com.projetointegrador.grupo04.movies_series.model.MovieModel

class MovieRepository {
    private val client = IMoviesEndpoint.endpoint

    suspend fun getPopularMovies() = client.getPopularMovies()
    suspend fun getTrendingMovies() = client.getTrendingMovies()
    suspend fun getLatestMovies() = client.getLatestMovies()
    suspend fun getUpcomingMovies() = client.getUpcomingMovies()
    suspend fun getTopRatedMovies() = client.getTopRatedMovies()
    suspend fun getNowPlayingMovies() = client.getNowPlayingMovies()

}