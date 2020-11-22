package com.projetointegrador.grupo04.movies_series.view

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.movies_series.model.MovieModel
import com.projetointegrador.grupo04.movies_series.repository.MovieRepository
import com.projetointegrador.grupo04.movies_series.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MoviesSeriesFragment : Fragment() {
    private lateinit var _movieListView: View
    private lateinit var _movieListViewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _movieListView = inflater.inflate(R.layout.fragment_series_movies, container, false)
        _movieListViewModel = ViewModelProvider(this,
            MovieListViewModel.ListRestaurantListViewModelFactory(MovieRepository())
        ).get(MovieListViewModel::class.java)

        _movieListViewModel.movieList.observe(this, Observer {
            createTrendingList(it)
            createAiringList(it)
            createPopularList(it)
            createUpcomingList(it)
        })

       _movieListViewModel.getList()

        setUserOptions()

        return _movieListView
    }

    fun createTrendingList(restaurant: List<MovieModel>) {
        val recyclerView = _movieListView.findViewById<RecyclerView>(R.id.rvMediaTrending)
        val manager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)

        val customAdapter = MovieListAdapter(restaurant) {
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = customAdapter
        }
    }

    fun createAiringList(restaurant: List<MovieModel>) {
        val recyclerView = _movieListView.findViewById<RecyclerView>(R.id.rvMediaAiring)
        val manager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)

        val customAdapter = MovieListAdapter(restaurant) {
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = customAdapter
        }
    }

    fun createPopularList(restaurant: List<MovieModel>) {
        val recyclerView = _movieListView.findViewById<RecyclerView>(R.id.rvMediaPopular)
        val manager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)

        val customAdapter = MovieListAdapter(restaurant) {
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = customAdapter
        }
    }

    fun createUpcomingList(restaurant: List<MovieModel>) {
        val recyclerView = _movieListView.findViewById<RecyclerView>(R.id.rvMediaUpcoming)
        val manager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)

        val customAdapter = MovieListAdapter(restaurant) {
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = customAdapter
        }
    }

    private fun setUserOptions() {
        _movieListView.findViewById<ImageView>(R.id.imgSeriesDetailsBack).setOnClickListener {
            Toast.makeText(context,"OPA OLHA AE",Toast.LENGTH_SHORT).show()
        }
    }
}