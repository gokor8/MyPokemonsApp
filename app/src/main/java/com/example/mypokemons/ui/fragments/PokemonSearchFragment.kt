package com.example.mypokemons.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.mypokemons.databinding.FragmentPokemonCardsBinding
import com.example.mypokemons.viewModels.BaseViewModel
import com.example.mypokemons.viewModels.SearchPokemonCardsViewModel

class PokemonSearchFragment() : PokemonCardsFragment() {

    override var viewModel: BaseViewModel? = null

    override var binding: FragmentPokemonCardsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        binding = FragmentPokemonCardsBinding.bind(view)
        viewModel = ViewModelProvider(this)[SearchPokemonCardsViewModel::class.java]

        createBaseLogic()

        (viewModel as SearchPokemonCardsViewModel).apply {
            val bundleName = requireArguments().getString(NAME).toString()
            updateDataByName(bundleName)
        }
    }

    companion object{

        const val NAME = "POKEMON_NAME"

        fun newInstance(name: String): PokemonSearchFragment{
            val args = Bundle()
            args.putString(NAME,name)

            val fragment = PokemonSearchFragment()
            fragment.arguments = args
            return fragment
        }
    }
}