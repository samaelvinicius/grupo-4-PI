package com.projetointegrador.grupo04.moviesseries.view

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.moviesseries.model.MovieModel
import com.projetointegrador.grupo04.moviesseries.repository.MovieRepository
import com.projetointegrador.grupo04.moviesseries.viewmodel.MovieListViewModel

class MoviesSeriesFragment : Fragment() {

    lateinit var _viewModel: MovieListViewModel
    lateinit var _view: View

    private lateinit var _latestListAdapter: MovieListAdapter
    private lateinit var _nowPlayingListAdapter: MovieListAdapter
    private lateinit var _popularListAdapter: MovieListAdapter
    private lateinit var _topRatedListAdapter: MovieListAdapter
    private lateinit var _trendingListAdapter: MovieListAdapter
    private lateinit var _upcomingListAdapter: MovieListAdapter

    private var _latestList = mutableListOf<MovieModel>()
    private var _nowPlayingList = mutableListOf<MovieModel>()
    private var _popularList = mutableListOf<MovieModel>()
    private var _topRatedList = mutableListOf<MovieModel>()
    private var _trendingList = mutableListOf<MovieModel>()
    private var _upcomingList = mutableListOf<MovieModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_series_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view
        _viewModel = ViewModelProvider(
            this,
            MovieListViewModel.MovieListViewModelFactory(MovieRepository())
        ).get(MovieListViewModel::class.java)

        //Manager initialization
        val trendingManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        val upcomingManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        val popularManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        val nowPlayingManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        //Recycler views identification
        val nowPlayingListRecycler = view.findViewById<RecyclerView>(R.id.rvMediaAiring)
        val popularListRecycler = view.findViewById<RecyclerView>(R.id.rvMediaPopular)
        val trendingListRecycler = view.findViewById<RecyclerView>(R.id.rvMediaTrending)
        val upcomingListRecycler = view.findViewById<RecyclerView>(R.id.rvMediaUpcoming)

        //Lists initialization
        _latestList = mutableListOf()
        _nowPlayingList = mutableListOf()
        _popularList = mutableListOf()
        _topRatedList = mutableListOf()
        _trendingList = mutableListOf()
        _upcomingList = mutableListOf()

        //Adapters initialization
        _nowPlayingListAdapter = MovieListAdapter(_nowPlayingList){
            val bundle = bundleOf(MOVIE_ID to it.id, MOVIE_TITLE to it.title, MOVIE_POSTER to it.posterPath,
                MOVIE_OVERVIEW to it.overview, MOVIE_RELEASE_DATE to it.releaseDate,
                MOVIE_VOTE_AVERAGE to it.voteAverage, MOVIE_BACKDROP to it.backdropPath)
            _view.findNavController().navigate(R.id.action_navigation_series_movies_to_movieDetailedFragment, bundle)
        }

        _popularListAdapter = MovieListAdapter(_popularList){}
        _topRatedListAdapter = MovieListAdapter(_topRatedList){}
        _trendingListAdapter = MovieListAdapter(_trendingList){}
        _upcomingListAdapter = MovieListAdapter(_upcomingList){}

        _viewModel.getNowPlayingMovies().observe(viewLifecycleOwner, {showResults(_nowPlayingListAdapter, _nowPlayingList, it)})
        _viewModel.getPopularMovies().observe(viewLifecycleOwner, {showResults(_popularListAdapter, _popularList, it)})
        _viewModel.getTrendingMovies().observe(viewLifecycleOwner, {showResults(_trendingListAdapter,_trendingList, it)})
        _viewModel.getUpcomingMovies().observe(viewLifecycleOwner, {showResults(_upcomingListAdapter, _upcomingList, it)})

        applyList(_nowPlayingListAdapter, nowPlayingListRecycler, nowPlayingManager)
        applyList(_popularListAdapter, popularListRecycler, popularManager)
        applyList(_trendingListAdapter, trendingListRecycler, trendingManager)
        applyList(_upcomingListAdapter, upcomingListRecycler, upcomingManager)

    }

    private fun applyList(adapterMain: MovieListAdapter, recyclerView: RecyclerView, manager: LinearLayoutManager){
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = adapterMain
        }
    }

    private fun showResults(adapter: MovieListAdapter,list: MutableList<MovieModel>?, element: List<MovieModel>?) {
        element?.let { list?.addAll(it) }
        adapter.notifyDataSetChanged()
    }

    companion object {
        const val MOVIE_ID = "ID"
        const val MOVIE_TITLE = "TITLE"
        const val MOVIE_POSTER = "POSTER"
        const val MOVIE_BACKDROP = "BACKDROP"
        const val MOVIE_OVERVIEW = "OVERVIEW"
        const val MOVIE_RELEASE_DATE = "RELEASE_DATE"
        const val MOVIE_VOTE_AVERAGE = "VOTE_AVERAGE"
    }
}