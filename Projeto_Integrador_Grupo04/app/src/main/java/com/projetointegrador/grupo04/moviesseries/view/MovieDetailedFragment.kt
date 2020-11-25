package com.projetointegrador.grupo04.moviesseries.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.moviesseries.model.CastModel
import com.projetointegrador.grupo04.moviesseries.model.MovieModel
import com.projetointegrador.grupo04.moviesseries.repository.MovieRepository
import com.projetointegrador.grupo04.moviesseries.viewmodel.CastListViewModel
import com.projetointegrador.grupo04.moviesseries.viewmodel.MovieListViewModel
import com.squareup.picasso.Picasso

class MovieDetailedFragment : Fragment() {

    lateinit var _viewModel: CastListViewModel
    lateinit var _view: View

    private lateinit var _castListAdapter: CastListAdapter
    private var _castList = mutableListOf<CastModel>()
    private var _movieId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_movie_detailed, container, false)
        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view
        _viewModel = ViewModelProvider(
            this,
            CastListViewModel.CastListViewModelFactory(MovieRepository())
        ).get(CastListViewModel::class.java)

        //Manager initialization
        val castManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        //Recycler views identification
        val castListRecycler = view.findViewById<RecyclerView>(R.id.rvDetailedCasting)

        //Lists initialization
        _castList = mutableListOf()

        //Adapters initialization
        _castListAdapter = CastListAdapter(_castList){}

        setBackNavigation(_view)
        setMovieDetailedInformation(_view)
        setUserOptions()

        _viewModel.getMovieCast(_movieId).observe(viewLifecycleOwner, {showResults(_castListAdapter, _castList, it)})

        applyList(_castListAdapter, castListRecycler, castManager)

    }

    private fun applyList(adapterMain: CastListAdapter, recyclerView: RecyclerView, manager: LinearLayoutManager){
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = adapterMain
        }
    }

    private fun showResults(adapter: CastListAdapter, list: MutableList<CastModel>?, element: List<CastModel>?) {
        element?.let { list?.addAll(it) }
        adapter.notifyDataSetChanged()
    }

    private fun setUserOptions() {
        _view.findViewById<ImageView>(R.id.iconUserTab).setOnClickListener {
            Toast.makeText(context,"Tentei abrir o menu aqui, mas não consegui achar a VIEW correta!",
                Toast.LENGTH_SHORT).show()
            //O COMANDO É ESTE ABAIXO, MAS NAO CONSEGUI LOCALIZAR A VIEW
            //findViewById<NavigationView>(R.id.navMenu).visibility = View.INVISIBLE
        }
    }

    private fun setMovieDetailedInformation(view: View) {

        _movieId = arguments?.getInt(MoviesSeriesFragment.MOVIE_ID)!!

        view.findViewById<TextView>(R.id.tvDetailedTitle).text = arguments?.getString(
            MoviesSeriesFragment.MOVIE_TITLE
        )

        view.findViewById<TextView>(R.id.tvDetailedOverview).text = arguments?.getString(
            MoviesSeriesFragment.MOVIE_OVERVIEW
        )

        view.findViewById<TextView>(R.id.tvDetailedVoteAverage).text = arguments?.getDouble(
            MoviesSeriesFragment.MOVIE_VOTE_AVERAGE
        ).toString()

        val posterImage = arguments?.getString(MoviesSeriesFragment.MOVIE_POSTER)
        if (posterImage != null) {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w342$posterImage")
                .into(view.findViewById<ImageView>(R.id.ivDetailedPoster))
        }

        val backdropImage = arguments?.getString(MoviesSeriesFragment.MOVIE_BACKDROP)
        if (backdropImage != null) {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w342$backdropImage")
                .into(view.findViewById<ImageView>(R.id.ivDetailedBackdrop))
        }

    }

    private fun setBackNavigation(view: View) {
        view.findViewById<ImageView>(R.id.imgReturn).setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
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