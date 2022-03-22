package com.example.mypokemons.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.*
import com.example.mypokemons.R
import com.example.mypokemons.databinding.FragmentPokemonCardsBinding
import com.example.mypokemons.viewModels.PokemonCardsViewModel

class PokemonCardsFragment() : BasePokemonCards(R.layout.fragment_pokemon_cards) {

    private var binding: FragmentPokemonCardsBinding? = null
    private var viewModel: PokemonCardsViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonCardsBinding.bind(view)
        viewModel = ViewModelProvider(this)[PokemonCardsViewModel::class.java]

        viewModel?.run {
            binding?.run {
                val baseBindings = BaseBindings(
                    recycleView,
                    pbWait,
                    tvNotify
                )
                setRecyclerViewLogic(baseBindings, pokemonsLiveData)
            }
            setPreviewPokemons()
        }
    }
}