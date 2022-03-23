package com.example.mypokemons.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mypokemons.R
import com.example.mypokemons.databinding.FragmentPokemonCardsBinding
import com.example.mypokemons.databinding.FragmentSearchPokemonCardsBinding
import com.example.mypokemons.ui.adapters.MainRecycleViewAdapter
import com.example.mypokemons.viewModels.PokemonCardsViewModel
import com.example.mypokemons.viewModels.SearchPokemonCardsViewModel

class SearchPokemonCardsFragment(private val searchedName: String) : BasePokemonCardsFragment(R.layout.fragment_search_pokemon_cards) {

    private var binding: FragmentSearchPokemonCardsBinding? = null
    private var viewModel: SearchPokemonCardsViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchPokemonCardsBinding.bind(view)
        viewModel = ViewModelProvider(this)[SearchPokemonCardsViewModel::class.java]

        viewModel?.run {
            binding?.run {
                val baseBindings = BaseBindings(
                    recycleView,
                    pbWait,
                    tvNotify
                )
                setRecyclerViewLogic(baseBindings, foundPokemonsLiveData)
            }
            search(searchedName)
        }
    }
}