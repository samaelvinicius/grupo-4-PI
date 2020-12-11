package com.projetointegrador.grupo04.moviesseries.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.moviesseries.repository.MediaRepository
import com.projetointegrador.grupo04.moviesseries.view.moviedetail.*
import com.projetointegrador.grupo04.moviesseries.viewmodel.MovieDetailViewModel
import com.squareup.picasso.Picasso


class MovieDetailFragment : Fragment() {

    lateinit var _viewModel: MovieDetailViewModel
    lateinit var _view: View

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    var _movieId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        _movieId = arguments?.getInt(MoviesSeriesFragment.MOVIE_ID)!!

        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view
        _viewModel = ViewModelProvider(
            this,
            MovieDetailViewModel.MovieDetailViewModelFactory(MediaRepository())
        ).get(MovieDetailViewModel::class.java)

        viewPager = _view.findViewById(R.id.my_view_pager)
        tabLayout = _view.findViewById(R.id.tabs)
        viewPager.adapter = MyFragmentPagerAdapter(requireContext(), childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        setBackNavigation(_view)

        val movieTitle = _view.findViewById<TextView>(R.id.tvDetailedTitle)
        val backdropImage = _view.findViewById<ImageView>(R.id.ivDetailedBackdrop)
        val posterImage = _view.findViewById<ImageView>(R.id.ivDetailedPoster)
        val voteAverage = _view.findViewById<TextView>(R.id.tvMediaScore)
        val releaseDate = _view.findViewById<TextView>(R.id.tvMediaReleaseDate)
        val runtime = _view.findViewById<TextView>(R.id.tvMediaRuntime)
        val voteCount = _view.findViewById<TextView>(R.id.tvVoteCount)

        _viewModel.getMovieDetail(_movieId).observe(viewLifecycleOwner) {
            movieTitle.text = it?.title
            voteAverage.text = it?.voteAverage.toString()
            releaseDate.text = it?.releaseDate
            runtime.text = "${it?.runtime.toString()} min"
            voteCount.text = it?.voteCount.toString()

            _view.findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar).setTitle(movieTitle.text)

            if (it?.posterPath != null) {
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w342${it?.posterPath}")
                    .into(posterImage)
            }

            if (it?.backdropPath != null) {
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w342${it?.backdropPath}")
                    .into(backdropImage)
            }
        }

    }

    private fun setBackNavigation(view: View) {
        view.findViewById<ImageView>(R.id.imgReturn).setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

    class MyFragmentPagerAdapter(
        private val context: Context,
        fragmentManager: FragmentManager
    ) : FragmentPagerAdapter(fragmentManager) {
        override fun getCount() = 3

        override fun getItem(position: Int) = when(position) {
            0 -> MovieAboutFragment()
            1 -> MovieCastFragment()
            2 -> MovieRecommendationsFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }

        override fun getPageTitle(position: Int): CharSequence = when(position) {
            0 -> "Sobre"
            1 -> "Elenco"//context.getString(R.string.second)
            2 -> "Recomendações"
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }

}
