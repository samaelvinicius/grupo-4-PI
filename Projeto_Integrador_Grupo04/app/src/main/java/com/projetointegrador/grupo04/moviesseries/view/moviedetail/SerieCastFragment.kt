package com.projetointegrador.grupo04.moviesseries.view.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.moviesseries.model.CastModel
import com.projetointegrador.grupo04.moviesseries.repository.MediaRepository
import com.projetointegrador.grupo04.moviesseries.view.MovieDetailFragment
import com.projetointegrador.grupo04.moviesseries.view.MoviesSeriesFragment
import com.projetointegrador.grupo04.moviesseries.view.moviedetail.MovieRecommendationsFragment.Companion.MOVIE_ID
import com.projetointegrador.grupo04.moviesseries.viewmodel.CastListViewModel

class SerieCastFragment : Fragment() {

    lateinit var _viewModel: CastListViewModel
    lateinit var _view: View

    private lateinit var _castListAdapter: CastListAdapter
    private var _castList = mutableListOf<CastModel>()
    private var _movieId: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_movie_cast, container, false)

        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view

        _viewModel = ViewModelProvider(
            this,
            CastListViewModel.CastListViewModelFactory(MediaRepository())
        ).get(CastListViewModel::class.java)

        //Data acquiring from parent fragment
        _movieId = (this.parentFragment as SerieDetailFragment?)?.arguments?.getInt(MoviesSeriesFragment.MOVIE_ID)


        //Manager initialization
        val castManager = GridLayoutManager(_view.context,3)

        //Recycler views identification
        val castListRecycler = _view.findViewById<RecyclerView>(R.id.rvDetailedCasting)

        //Lists initialization
        _castList = mutableListOf()

        //Adapters initialization
        _castListAdapter =
            CastListAdapter(
                _castList
            ) {}
        _viewModel.getSerieCast(_movieId).observe(viewLifecycleOwner, {showResults(_castListAdapter, _castList, it)})

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


}