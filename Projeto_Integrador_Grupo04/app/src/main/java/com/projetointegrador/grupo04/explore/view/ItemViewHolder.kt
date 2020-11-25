package com.projetointegrador.grupo04.explore.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.explore.model.PersonagemModel
import com.squareup.picasso.Picasso

class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    private val imagem = view.findViewById<ImageView>(R.id.ivPoster)
    private val nome = view.findViewById<TextView>(R.id.tvMediaName)

    fun bind(personagemModel: PersonagemModel) {
        nome.text = personagemModel.nome

        Picasso.get()
            .load("https://image.tmdb.org/t/p/w342" + personagemModel.posterPath)
            .into(imagem)
    }
}
