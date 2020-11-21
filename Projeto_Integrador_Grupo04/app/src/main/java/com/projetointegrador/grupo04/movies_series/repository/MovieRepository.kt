package com.projetointegrador.grupo04.movies_series.repository

import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.movies_series.model.MovieModel

class MovieRepository {
    var moviesListed = mutableListOf<MovieModel>(
        MovieModel(
            "FILME",
            90,
            R.drawable.cover_django
        ),
        MovieModel(
            "FILME",
            90,
            R.drawable.cover_django
        ),
        MovieModel(
            "FILME",
            90,
            R.drawable.cover_django
        ),
        MovieModel(
            "FILME",
            90,
            R.drawable.cover_django
        ),
        MovieModel(
            "FILME",
            90,
            R.drawable.cover_django
        ),
        MovieModel(
            "FILME",
            90,
            R.drawable.cover_django
        ),
        MovieModel(
            "FILME",
            90,
            R.drawable.cover_django
        ),
        MovieModel(
            "FILME",
            90,
            R.drawable.cover_django
        ),
        MovieModel(
            "FILME",
            90,
            R.drawable.cover_django
        ),
        MovieModel(
            "FILME",
            90,
            R.drawable.cover_django
        ),
        MovieModel(
            "FILME",
            90,
            R.drawable.cover_django
        ),
        MovieModel(
            "FILME",
            90,
            R.drawable.cover_django
        ),
        MovieModel(
            "FILME",
            90,
            R.drawable.cover_django
        )
    )

    fun getMovieList(): MutableList<MovieModel> {
        return moviesListed
    }

    fun getList(callback: (movie: List<MovieModel>) -> Unit) {
        callback.invoke(getMovieList())
    }
}