package com.projetointegrador.grupo04.moviesseries.view.MovieDetail

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.moviesseries.model.CastModel
import com.squareup.picasso.Picasso

class CastListViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val _name = view.findViewById<TextView>(R.id.tvCastName)
    private val _picture = view.findViewById<ImageView>(R.id.ivCastPicture)
    private var _movieId: Int = 0

    fun bind(cast: CastModel){
        _name.text = cast.name
        _movieId = cast.id

        Picasso.get()
            .load("https://image.tmdb.org/t/p/w342" + cast.profilePath)
            .into(_picture)
    }
}