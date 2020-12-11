package com.projetointegrador.grupo04.moviesseries.view.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.moviesseries.model.CastModel

class CastListAdapter(private val dataset: List<CastModel>,
                      private val listener: (CastModel) -> Unit):
    RecyclerView.Adapter<CastListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.example_cast, parent, false)
        return CastListViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: CastListViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount() = dataset.size
}