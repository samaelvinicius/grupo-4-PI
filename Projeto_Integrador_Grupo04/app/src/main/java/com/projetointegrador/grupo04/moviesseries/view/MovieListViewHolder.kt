package com.projetointegrador.grupo04.moviesseries.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.moviesseries.model.MediaModel
import com.squareup.picasso.Picasso

class MovieListViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    private val _title = view.findViewById<TextView>(R.id.tvMediaName)
    private val _voteAverage = view.findViewById<TextView>(R.id.tvMediaScore)
    private val _poster = view.findViewById<ImageView>(R.id.ivPoster)
    private var _movieId: Int = 0

    fun bind(media: MediaModel){
        if(media.title != null){
            _title.text = media.title
        }else if(media.name != null){
            _title.text = media.name
        }

        _voteAverage.text = media.voteAverage.toString()
        _movieId = media.id

        Picasso.get()
            .load("https://image.tmdb.org/t/p/w342" + media.posterPath)
            .into(_poster)
    }
}