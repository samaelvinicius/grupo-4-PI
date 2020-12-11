package com.projetointegrador.grupo04.moviesseries.view

import android.graphics.Typeface
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.moviesseries.model.MediaModel
import com.projetointegrador.grupo04.moviesseries.repository.MediaRepository
import com.projetointegrador.grupo04.moviesseries.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.nav_header_main.*

class MoviesSeriesFragment : Fragment() {

    lateinit var _viewModel: MovieListViewModel
    lateinit var _view: View

    private var _nowPlayingList = mutableListOf<MediaModel>()
    private var _onTheAirList = mutableListOf<MediaModel>()
    private var _trendingMoviesList = mutableListOf<MediaModel>()
    private var _trendingSeriesList = mutableListOf<MediaModel>()
    private var _popularMoviesList = mutableListOf<MediaModel>()
    private var _popularSeriesList = mutableListOf<MediaModel>()
    private var _topRatedMoviesList = mutableListOf<MediaModel>()
    private var _topRatedSeriesList = mutableListOf<MediaModel>()

    private var _recNowPlaying: Int = R.id.rvMediaAiring
    private var _recPopular: Int = R.id.rvMediaPopular
    private var _recTopRated: Int = R.id.rvMediaTopRated
    private var _recTrending: Int = R.id.rvMediaTrending
    private val _navActionMovie = R.id.action_navigation_series_movies_to_movieDetailedFragment
    private val _navActionSerie = R.id.action_navigation_series_movies_to_seriesDetailFragment

    private var switchNowPlaying: SwitchMaterial? = null
    private lateinit var _switchTextMovies: TextView
    private lateinit var _switchTextSeries: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_series_movies, container, false)
        _viewModel = ViewModelProvider(
            this,
            MovieListViewModel.MovieListViewModelFactory(MediaRepository())
        ).get(MovieListViewModel::class.java)

        _switchTextMovies = (activity as AppCompatActivity).findViewById<TextView>(R.id.tvSwitchMovies)
        _switchTextSeries = (activity as AppCompatActivity).findViewById<TextView>(R.id.tvSwitchSeries)

        //Recyclers initializatio
        switchNowPlaying = (activity as AppCompatActivity).findViewById(R.id.swAiring)

        if(switchNowPlaying?.isChecked!!){
            _switchTextSeries.setTypeface(_switchTextSeries.getTypeface(), Typeface.BOLD)
            setMediaList(_viewModel.getSeriesOnTheAir(), _onTheAirList, _recNowPlaying, _navActionSerie,_view)
            setMediaList(_viewModel.getTrendingSeries(), _trendingSeriesList, _recTrending, _navActionSerie,_view)
            setMediaList(_viewModel.getPopularSeries(), _popularSeriesList, _recPopular, _navActionSerie,_view)
            setMediaList(_viewModel.getTopRatedSeries(), _topRatedSeriesList, _recTopRated, _navActionSerie,_view)
        }else{
            _switchTextMovies.setTypeface(_switchTextMovies.getTypeface(), Typeface.BOLD)
            setMediaList(_viewModel.getNowPlayingMovies(), _nowPlayingList, _recNowPlaying, _navActionMovie,_view)
            setMediaList(_viewModel.getTrendingMovies(), _trendingMoviesList, _recTrending, _navActionMovie,_view)
            setMediaList(_viewModel.getPopularMovies(), _popularMoviesList, _recPopular, _navActionMovie,_view)
            setMediaList(_viewModel.getTopRatedMovies(), _topRatedMoviesList, _recTopRated, _navActionMovie,_view)
        }

        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view

        //Adapters initialization
        switchNowPlaying?.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                _switchTextMovies.setTypeface(null, Typeface.NORMAL)
                _switchTextSeries.setTypeface(null, Typeface.BOLD)
                setMediaList(_viewModel.getSeriesOnTheAir(), _onTheAirList, _recNowPlaying, _navActionSerie,_view)
                setMediaList(_viewModel.getTrendingSeries(), _trendingSeriesList, _recTrending, _navActionSerie,_view)
                setMediaList(_viewModel.getPopularSeries(), _popularSeriesList, _recPopular, _navActionSerie,_view)
                setMediaList(_viewModel.getTopRatedSeries(), _topRatedSeriesList, _recTopRated, _navActionSerie,_view)
            }else{
                _switchTextMovies.setTypeface(null, Typeface.BOLD)
                _switchTextSeries.setTypeface(null, Typeface.NORMAL)
                setMediaList(_viewModel.getNowPlayingMovies(), _nowPlayingList, _recNowPlaying, _navActionMovie,_view)
                setMediaList(_viewModel.getTrendingMovies(), _trendingMoviesList, _recTrending, _navActionMovie,_view)
                setMediaList(_viewModel.getPopularMovies(), _popularSeriesList, _recPopular, _navActionMovie,_view)
                setMediaList(_viewModel.getTopRatedMovies(), _topRatedSeriesList, _recTopRated, _navActionMovie,_view)
            }
        }

    }

    fun setMediaList(method: LiveData<List<MediaModel>>, mediaList: MutableList<MediaModel>,
                     recyclerId: Int, navAction: Int, view: View){

        mediaList.clear()
        val recycler = view.findViewById<RecyclerView>(recyclerId)
        val manager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        val listAdapter: MovieListAdapter

        listAdapter = MovieListAdapter(mediaList){
            val bundle = bundleOf(MOVIE_ID to it.id)
            view.findNavController().navigate(navAction, bundle)
        }

        method.observe(viewLifecycleOwner) {showResults(listAdapter, mediaList, it)}
        applyList(listAdapter, recycler, manager)

    }

    private fun applyList(adapterMain: MovieListAdapter, recyclerView: RecyclerView, manager: LinearLayoutManager){
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = adapterMain
        }
    }

    private fun showResults(adapter: MovieListAdapter, list: MutableList<MediaModel>?, element: List<MediaModel>?) {
        element?.let { list?.addAll(it) }
        element?.let {println(it)}

        adapter.notifyDataSetChanged()
    }

    companion object {
        const val MOVIE_ID = "ID"
    }
}