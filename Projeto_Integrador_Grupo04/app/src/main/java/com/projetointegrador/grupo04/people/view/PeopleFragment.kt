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
    lateinit var movieListView: View
    lateinit var movieListViewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        movieListView = inflater.inflate(R.layout.fragment_people, container, false)
        movieListViewModel = ViewModelProvider(this,
            MovieListViewModel.ListRestaurantListViewModelFactory(MovieRepository())
        ).get(MovieListViewModel::class.java)

        movieListViewModel.movieList.observe(this, Observer {
            createTrendingList(it)
        })

        movieListViewModel.getList()
        return movieListView
    }

    fun createTrendingList(restaurant: List<MovieModel>) {
        val recyclerView = movieListView.findViewById<RecyclerView>(R.id.rvMediaTrending2)
        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val customAdapter = MovieListAdapter(restaurant) {
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = customAdapter
        }
    }
}