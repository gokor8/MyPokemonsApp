package com.example.mypokemons.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.mypokemons.R
import com.example.mypokemons.databinding.FragmentPokemonFavoritesBinding
import com.example.mypokemons.viewModels.PokemonFavoritesViewModel

class PokemonFavoritesFragment() : BasePokemonCardsFragment(R.layout.fragment_pokemon_favorites) {

    private var binding: FragmentPokemonFavoritesBinding? = null
    private var viewModel: PokemonFavoritesViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonFavoritesBinding.bind(view)
        viewModel = ViewModelProvider(this)[PokemonFavoritesViewModel::class.java]

        viewModel?.run {
            binding?.run {
                val baseBindings = BaseBindings(
                    recycleView,
                    pbWait,
                    tvNotify
                )
                setRecyclerViewLogic(baseBindings, pokemonsLiveData)
            }
            setFavorites()
        }
    }
}