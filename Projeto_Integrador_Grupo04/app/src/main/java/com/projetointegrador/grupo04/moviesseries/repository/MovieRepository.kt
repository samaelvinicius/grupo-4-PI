package com.projetointegrador.grupo04.moviesseries.repository

class MovieRepository {
    private val client = IMoviesEndpoint.endpoint

    suspend fun getPopularMovies() = client.getPopularMovies()
    suspend fun getTrendingMovies() = client.getTrendingMovies()
    suspend fun getLatestMovies() = client.getLatestMovies()
    suspend fun getUpcomingMovies() = client.getUpcomingMovies()
    suspend fun getTopRatedMovies() = client.getTopRatedMovies()
    suspend fun getNowPlayingMovies() = client.getNowPlayingMovies()
    suspend fun getMovieCast(movieId: Int?) = movieId?.let { client.getMovieCast(it) }
    suspend fun getMovieDetail(movieId: Int?) = movieId?.let { client.getMovieDetail(it) }

}