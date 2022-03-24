package com.example.mypokemons.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.mypokemons.databinding.FragmentPokemonCardsBinding
import com.example.mypokemons.viewModels.BaseViewModel
import com.example.mypokemons.viewModels.PokemonFavoritesViewModel

class PokemonFavoritesFragment() : PokemonCardsFragment() {

    override var viewModel: BaseViewModel? = null

    override var binding: FragmentPokemonCardsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        binding = FragmentPokemonCardsBinding.bind(view)
        viewModel = ViewModelProvider(this)[PokemonFavoritesViewModel::class.java]

        createBaseLogic()
        viewModel?.updateData()
    }
}