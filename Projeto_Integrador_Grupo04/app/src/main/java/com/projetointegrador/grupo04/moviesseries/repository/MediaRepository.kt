package com.projetointegrador.grupo04.moviesseries.repository

class MediaRepository {
    private val client = IMediaEndpoint.endpoint

    suspend fun getPopularMovies() = client.getPopularMovies()
    suspend fun getPopularSeries() = client.getPopularSeries()
    suspend fun getTrendingMovies() = client.getTrendingMovies()
    suspend fun getTrendingSeries() = client.getTrendingSeries()
    suspend fun getTopRatedMovies() = client.getTopRatedMovies()
    suspend fun getTopRatedSeries() = client.getTopRatedSeries()
    suspend fun getNowPlayingMovies() = client.getNowPlayingMovies()
    suspend fun getMovieCast(movieId: Int?) = movieId?.let { client.getMovieCast(it) }
    suspend fun getSerieCast(movieId: Int?) = movieId?.let { client.getSerieCast(it) }
    suspend fun getMovieRecommendations(movieId: Int?) = movieId?.let { client.getMovieRecommendations(it) }
    suspend fun getMovieDetail(id: Int?) = id?.let { client.getMovieDetail(it) }
    suspend fun getSerieDetail(id: Int?) = id?.let { client.getSerieDetail(it) }
    suspend fun getSeriesOnTheAir() = client.getSeriesOnTheAir()

}