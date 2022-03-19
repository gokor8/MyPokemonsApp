package com.example.mypokemons.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mypokemons.R
import com.example.mypokemons.databinding.FragmentSearchPokemonCardsBinding
import com.example.mypokemons.ui.adapters.MainRecycleViewAdapter
import com.example.mypokemons.view_models.SearchPokemonCardsViewModel

class SearchPokemonCardsFragment(private val searchedName: String) : Fragment(R.layout.fragment_search_pokemon_cards) {

    private var viewBinding: FragmentSearchPokemonCardsBinding? = null

    private lateinit var viewModel: SearchPokemonCardsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentSearchPokemonCardsBinding.bind(view)
        viewModel = ViewModelProvider(this).get(SearchPokemonCardsViewModel::class.java)

        val rvAdapter = MainRecycleViewAdapter()

        viewBinding?.recycleView?.run {
            layoutManager = GridLayoutManager(this.context, 2)
            adapter = rvAdapter
        }

        viewModel.foundPokemonsLiveData.observe(this as LifecycleOwner, Observer {

            viewBinding?.pbWait?.visibility = View.INVISIBLE

            if (it == null || it.pokemons.isEmpty())
                viewBinding?.tvNotify?.visibility = View.VISIBLE
            else {
                viewBinding?.tvNotify?.visibility = View.INVISIBLE

                rvAdapter.refreshAdapter(it.pokemons)
            }
        })

        viewModel.search(searchedName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}