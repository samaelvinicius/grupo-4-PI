package com.projetointegrador.grupo04.movies_series.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.movies_series.model.MovieModel

class MovieListViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    private val name = view.findViewById<TextView>(R.id.tvMediaName)
    private val score = view.findViewById<TextView>(R.id.tvMediaScore)
    private val image = view.findViewById<ImageView>(R.id.ivPoster)

    fun bind(movie: MovieModel){
        name.text = movie.name
        score.text = "${movie.score}%"
        image.setImageDrawable(ContextCompat.getDrawable(view.context, movie.image))
    }
}