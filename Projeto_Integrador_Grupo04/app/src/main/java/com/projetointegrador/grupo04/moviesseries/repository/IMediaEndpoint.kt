package com.projetointegrador.grupo04.moviesseries.repository

import com.projetointegrador.grupo04.data.api.NetworkUtils
import com.projetointegrador.grupo04.data.model.CreditsResponseModel
import com.projetointegrador.grupo04.data.model.ResponseModel
import com.projetointegrador.grupo04.moviesseries.model.CastModel
import com.projetointegrador.grupo04.moviesseries.model.MovieDetailModel
import com.projetointegrador.grupo04.moviesseries.model.MediaModel
import com.projetointegrador.grupo04.moviesseries.model.SerieDetailModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IMediaEndpoint {

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(@Path("movie_id") movieId: Int?): CreditsResponseModel<CastModel>

    @GET("tv/{tv_id}/credits")
    suspend fun getSerieCast(@Path("tv_id") Id: Int?): CreditsResponseModel<CastModel>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: Int?): MovieDetailModel

    @GET("movie/{movie_id}/recommendations")
    suspend fun getMovieRecommendations(@Path("movie_id") id: Int?): ResponseModel<MediaModel>

    @GET("tv/{tv_id}")
    suspend fun getSerieDetail(@Path("tv_id") id: Int?): SerieDetailModel

    @GET("movie/popular")
    suspend fun getPopularMovies(): ResponseModel<MediaModel>

    @GET("tv/popular")
    suspend fun getPopularSeries(): ResponseModel<MediaModel>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("region") region: String? = "BR"): ResponseModel<MediaModel>

    @GET("tv/on_the_air")
    suspend fun getSeriesOnTheAir(@Query("region") region: String? = "BR"): ResponseModel<MediaModel>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): ResponseModel<MediaModel>

    @GET("tv/top_rated")
    suspend fun getTopRatedSeries(): ResponseModel<MediaModel>

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(): ResponseModel<MediaModel>

    @GET("trending/tv/day")
    suspend fun getTrendingSeries(): ResponseModel<MediaModel>

    companion object {
        val endpoint: IMediaEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IMediaEndpoint::class.java)
        }
    }
}