package com.projetointegrador.grupo04.movies_series.repository

import com.projetointegrador.grupo04.data.api.NetworkUtils
import com.projetointegrador.grupo04.data.model.ResponseModel
import com.projetointegrador.grupo04.movies_series.model.MovieModel
import retrofit2.http.GET
import retrofit2.http.Query

interface IMoviesEndpoint {
    @GET("movie/popular")
    suspend fun getPopularMovies(): ResponseModel<MovieModel>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("region") region: String? = "BR"): ResponseModel<MovieModel>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("region") region: String? = "BR"): ResponseModel<MovieModel>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): ResponseModel<MovieModel>

    @GET("movie/latest")
    suspend fun getLatestMovies(): ResponseModel<MovieModel>

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(): ResponseModel<MovieModel>

    companion object {
        val endpoint: IMoviesEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IMoviesEndpoint::class.java)
        }
    }
}