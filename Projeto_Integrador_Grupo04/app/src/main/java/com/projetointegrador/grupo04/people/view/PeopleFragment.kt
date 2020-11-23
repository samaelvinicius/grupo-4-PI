package com.projetointegrador.grupo04.people.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.movies_series.model.MovieModel
import com.projetointegrador.grupo04.movies_series.repository.MovieRepository
import com.projetointegrador.grupo04.movies_series.view.MovieListAdapter
import com.projetointegrador.grupo04.movies_series.viewmodel.MovieListViewModel

class PeopleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_people, container, false)

    }
}