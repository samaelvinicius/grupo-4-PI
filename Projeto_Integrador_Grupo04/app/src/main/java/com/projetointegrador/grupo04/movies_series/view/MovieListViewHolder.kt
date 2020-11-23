package com.projetointegrador.grupo04.movies_series.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.movies_series.model.MovieModel
import com.squareup.picasso.Picasso

class MovieListViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    private val title = view.findViewById<TextView>(R.id.tvMediaName)
    private val voteAverage = view.findViewById<TextView>(R.id.tvMediaScore)
    private val poster = view.findViewById<ImageView>(R.id.ivPoster)

    fun bind(movie: MovieModel){
        title.text = movie.title
        voteAverage.text = movie.voteAverage.toString()

        Picasso.get()
            .load("https://image.tmdb.org/t/p/w342" + movie.posterPath)
            .into(poster)
    }
}