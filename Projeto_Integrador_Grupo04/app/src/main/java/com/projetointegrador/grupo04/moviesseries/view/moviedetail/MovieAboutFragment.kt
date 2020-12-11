package com.projetointegrador.grupo04.moviesseries.view.moviedetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.google.android.material.chip.Chip
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.moviesseries.model.GenreModel
import com.projetointegrador.grupo04.moviesseries.repository.MediaRepository
import com.projetointegrador.grupo04.moviesseries.view.MoviesSeriesFragment
import com.projetointegrador.grupo04.moviesseries.viewmodel.MovieDetailViewModel
import kotlinx.android.synthetic.main.fragment_about_movie.*
import com.projetointegrador.grupo04.moviesseries.view.MovieDetailFragment as MovieDetailFragment1


class MovieAboutFragment : Fragment() {

    lateinit var _viewModel: MovieDetailViewModel
    lateinit var _view: View

    private var _movieId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_about_movie, container, false)

        return _view
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view
        _viewModel = ViewModelProvider(
            this,
            MovieDetailViewModel.MovieDetailViewModelFactory(MediaRepository())
        ).get(MovieDetailViewModel::class.java)

        _movieId = (this.parentFragment as MovieDetailFragment1?)?.arguments?.getInt(
            MoviesSeriesFragment.MOVIE_ID)!!

        val movieOverview = _view.findViewById<TextView>(R.id.tvMovieOverview)
        val originalLanguage = _view.findViewById<TextView>(R.id.tvMovieOriginalLanguage)
        val originalTitle = _view.findViewById<TextView>(R.id.tvMovieOriginalTitle)
        val budget = _view.findViewById<TextView>(R.id.tvMovieBudget)
        val revenue = _view.findViewById<TextView>(R.id.tvMovieRevenue)

        _viewModel.getMovieDetail(_movieId).observe(viewLifecycleOwner) {
            movieOverview.text = it?.overview
            originalLanguage.text = it?.originalLanguage
            originalTitle.text = it?.originalTitle
            budget.text = "$ ${it?.budget.toString()}"
            revenue.text = "$ ${it?.revenue.toString()}"
            createGenreChips(it!!.genres)
        }


    }

    fun createGenreChips(genres: List<GenreModel>){

        genres.forEach { it ->
            val chip = Chip(chipGroup.context)
            chip.text= "${it.genreName}"

            // necessary to get single selection working
            chip.isClickable = false
            chip.isCheckable = false
            chipGroup.addView(chip)
        }
    }

}