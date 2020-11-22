package com.projetointegrador.grupo04.explore.view

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

class ExploreFragment : Fragment() {
    lateinit var movieListView: View
    lateinit var movieListViewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        movieListView = inflater.inflate(R.layout.fragment_explore, container, false)
        movieListViewModel = ViewModelProvider(this,
            MovieListViewModel.ListRestaurantListViewModelFactory(MovieRepository())
        ).get(MovieListViewModel::class.java)

        movieListViewModel.movieList.observe(this, Observer {
            createTrendingList(it)
            createTrendingList2(it)
//            createTrendingList3(it)
//            createTrendingList4(it)
        })

        movieListViewModel.getList()
        return movieListView
    }

    fun createTrendingList(restaurant: List<MovieModel>) {
        val recyclerView = movieListView.findViewById<RecyclerView>(R.id.rvExplore)
        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        val customAdapter = MovieListAdapter(restaurant) {
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = customAdapter
        }
    }
    fun createTrendingList2(restaurant: List<MovieModel>) {
        val recyclerView = movieListView.findViewById<RecyclerView>(R.id.rvExplore2)
        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        val customAdapter = MovieListAdapter(restaurant) {
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = customAdapter
        }
    }
//    fun createTrendingList3(restaurant: List<MovieModel>) {
//        val recyclerView = movieListView.findViewById<RecyclerView>(R.id.rvExplore3)
//        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
//
//        val customAdapter = MovieListAdapter(restaurant) {
//        }
//
//        recyclerView.apply {
//            setHasFixedSize(true)
//            layoutManager = manager
//            adapter = customAdapter
//        }
//    }
//    fun createTrendingList4(restaurant: List<MovieModel>) {
//        val recyclerView = movieListView.findViewById<RecyclerView>(R.id.rvExplore4)
//        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
//
//        val customAdapter = MovieListAdapter(restaurant) {
//        }
//
//        recyclerView.apply {
//            setHasFixedSize(true)
//            layoutManager = manager
//            adapter = customAdapter
//        }
//    }
}