package com.projetointegrador.grupo04.moviesseries.view.moviedetail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.explore.view.ExploreFragment
import com.projetointegrador.grupo04.moviesseries.repository.MediaRepository
import com.projetointegrador.grupo04.moviesseries.view.MovieDetailFragment
import com.projetointegrador.grupo04.moviesseries.view.MoviesSeriesFragment
import com.projetointegrador.grupo04.moviesseries.viewmodel.MovieDetailViewModel
import com.squareup.picasso.Picasso

class SerieDetailFragment : Fragment() {

    lateinit var _viewModel: MovieDetailViewModel
    lateinit var _view: View

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    var _id: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_serie_detail, container, false)

        _id = arguments?.getInt(MoviesSeriesFragment.MOVIE_ID)!!

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

        val serieTitle = _view.findViewById<TextView>(R.id.tvDetailedTitle)
        val backdropImage = _view.findViewById<ImageView>(R.id.ivDetailedBackdrop)
        val posterImage = _view.findViewById<ImageView>(R.id.ivDetailedPoster)
        val voteAverage = _view.findViewById<TextView>(R.id.tvMediaScore)
        val releaseDate = _view.findViewById<TextView>(R.id.tvMediaReleaseDate)
        val runtime = _view.findViewById<TextView>(R.id.tvMediaRuntime)
        val voteCount = _view.findViewById<TextView>(R.id.tvVoteCount)

        _viewModel.getSerieDetail(_id).observe(viewLifecycleOwner) {
            serieTitle.text = it?.name
            voteAverage.text = it?.voteAverage.toString()
            voteCount.text = it?.voteCount.toString()

            _view.findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar).setTitle(serieTitle.text)

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
//
    class MyFragmentPagerAdapter(
        private val context: Context,
        fragmentManager: FragmentManager
    ) : FragmentPagerAdapter(fragmentManager) {
        override fun getCount() = 2

        override fun getItem(position: Int) = when(position) {
            0 -> SerieAboutFragment()
            1 -> SerieCastFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }

        override fun getPageTitle(position: Int): CharSequence = when(position) {
            0 -> "Sobre"
            1 -> "Elenco"//context.getString(R.string.second)
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }

}