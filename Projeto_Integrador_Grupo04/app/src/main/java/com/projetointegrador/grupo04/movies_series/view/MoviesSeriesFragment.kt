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
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.explore.model.PersonagemModel
import com.projetointegrador.grupo04.explore.repository.PersonagemRepository
import com.projetointegrador.grupo04.explore.view.ListaAdapter
import com.projetointegrador.grupo04.explore.viewmodel.PersonagemViewModel
import com.projetointegrador.grupo04.movies_series.model.MovieModel
import com.projetointegrador.grupo04.movies_series.repository.MovieRepository
import com.projetointegrador.grupo04.movies_series.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_main.*

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
        _nowPlayingListAdapter = MovieListAdapter(_nowPlayingList){}
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

        setUserOptions()
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

    private fun setUserOptions() {
        _view.findViewById<ImageView>(R.id.iconUserTab).setOnClickListener {
            Toast.makeText(context,"Tentei abrir o menu aqui, mas não consegui achar a VIEW correta!",Toast.LENGTH_SHORT).show()
            //O COMANDO É ESTE ABAIXO, MAS NAO CONSEGUI LOCALIZAR A VIEW
            //findViewById<NavigationView>(R.id.navMenu).visibility = View.INVISIBLE
        }
    }
}