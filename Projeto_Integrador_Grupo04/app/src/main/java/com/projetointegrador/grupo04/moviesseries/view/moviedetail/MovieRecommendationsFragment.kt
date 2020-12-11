package com.projetointegrador.grupo04.moviesseries.view.moviedetail

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.moviesseries.model.CastModel
import com.projetointegrador.grupo04.moviesseries.model.MediaModel
import com.projetointegrador.grupo04.moviesseries.repository.MediaRepository
import com.projetointegrador.grupo04.moviesseries.view.MovieDetailFragment
import com.projetointegrador.grupo04.moviesseries.view.MovieListAdapter
import com.projetointegrador.grupo04.moviesseries.view.MoviesSeriesFragment
import com.projetointegrador.grupo04.moviesseries.viewmodel.CastListViewModel
import com.projetointegrador.grupo04.moviesseries.viewmodel.MovieListViewModel

class MovieRecommendationsFragment : Fragment() {
    lateinit var _viewModel: MovieListViewModel
    lateinit var _view: View

    private var _reoommendationList = mutableListOf<MediaModel>()

    private var _recycler: Int = R.id.rvDetailedRecommendations
    private val _navActionMovie = R.id.action_movieReviewFragment_to_navigation_detail_movie

    var _movieId: Int = 0

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_movie_review, container, false)
        _viewModel = ViewModelProvider(
            this,
            MovieListViewModel.MovieListViewModelFactory(MediaRepository())
        ).get(MovieListViewModel::class.java)

        _movieId = (this.parentFragment as MovieDetailFragment?)?.arguments?.getInt(MoviesSeriesFragment.MOVIE_ID)!!

        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view

        setMediaList(_viewModel.getMovieRecommendations(_movieId), _reoommendationList, _recycler, _navActionMovie, _view)
    }

    fun setMediaList(
        method: LiveData<List<MediaModel>>, mediaList: MutableList<MediaModel>,
        recyclerId: Int, navAction: Int, view: View
    ) {

        mediaList.clear()
        val recycler = view.findViewById<RecyclerView>(recyclerId)
        val manager = GridLayoutManager(view.context,3)
        val listAdapter: MovieListAdapter

        listAdapter = MovieListAdapter(mediaList) {
            val bundle = bundleOf(MOVIE_ID to it.id)
            view.findNavController().navigate(navAction, bundle)
        }

        method.observe(viewLifecycleOwner) { showResults(listAdapter, mediaList, it) }
        applyList(listAdapter, recycler, manager)

    }

    private fun applyList(
        adapterMain: MovieListAdapter,
        recyclerView: RecyclerView,
        manager: LinearLayoutManager
    ) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = adapterMain
        }
    }

    private fun showResults(
        adapter: MovieListAdapter,
        list: MutableList<MediaModel>?,
        element: List<MediaModel>?
    ) {
        element?.let { list?.addAll(it) }
        element?.let { println(it) }

        adapter.notifyDataSetChanged()
    }

    companion object {
        const val MOVIE_ID = "ID"
    }
}