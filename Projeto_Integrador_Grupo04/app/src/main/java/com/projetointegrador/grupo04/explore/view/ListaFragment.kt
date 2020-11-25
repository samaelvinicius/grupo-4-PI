package com.projetointegrador.grupo04.explore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projetointegrador.grupo04.R
import com.projetointegrador.grupo04.explore.model.PersonagemModel
import com.projetointegrador.grupo04.explore.repository.PersonagemRepository
import com.projetointegrador.grupo04.explore.viewmodel.PersonagemViewModel

class ListaFragment : Fragment() {
    lateinit var _viewModel: PersonagemViewModel
    lateinit var _view: View
    private lateinit var _listaAdapter: ListaAdapter

    private var _listaDePersonagens = mutableListOf<PersonagemModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view = view

        val lista = view.findViewById<RecyclerView>(R.id.rvExplore)
        val manager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        _listaDePersonagens = mutableListOf()
        _listaAdapter = ListaAdapter(_listaDePersonagens)

        lista.apply {
            setHasFixedSize(true)

            layoutManager = manager
            adapter = _listaAdapter
        }

        _viewModel = ViewModelProvider(
            this,
            PersonagemViewModel.PersonagemViewModelFactory(PersonagemRepository())
        ).get(PersonagemViewModel::class.java)

        _viewModel.obterLista().observe(viewLifecycleOwner, {
            exibirResultados(it)
        })

        //showLoading(true)
    }

    private fun exibirResultados(lista: List<PersonagemModel>?) {
        //showLoading(false)

        //lista?.isNotEmpty()?.let { notfound(it) }

        lista?.let { _listaDePersonagens.addAll(it) }

        _listaAdapter.notifyDataSetChanged()
    }

//    fun showLoading(isLoading: Boolean) {
//        val viewLoading = _view.findViewById<View>(R.id.loading)
//
//        if (isLoading) {
//            viewLoading.visibility = View.VISIBLE
//        } else {
//            viewLoading.visibility = View.GONE
//        }
//    }

//    fun notfound(notfound: Boolean) {
//        if (notfound) {
//            _view.findViewById<View>(R.id.notfoundLayout).visibility = View.GONE
//        } else {
//            _view.findViewById<View>(R.id.notfoundLayout).visibility = View.VISIBLE
//        }
//    }
}