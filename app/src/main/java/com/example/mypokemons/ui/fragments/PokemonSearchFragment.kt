package com.example.mypokemons.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mypokemons.databinding.FragmentPokemonCardsBinding
import com.example.mypokemons.viewModels.BaseViewModel
import com.example.mypokemons.viewModels.SearchPokemonCardsViewModel

class PokemonSearchFragment() : PokemonCardsFragment() {

    override var viewModel: BaseViewModel? = null

    override var binding: FragmentPokemonCardsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPokemonCardsBinding.bind(view)
        viewModel = ViewModelProvider(this)[SearchPokemonCardsViewModel::class.java]

        createBaseLogic()
        (viewModel as SearchPokemonCardsViewModel).name = requireArguments().getString(NAME).toString()
        viewModel?.updateData()
    }

    companion object{

        val NAME = "NAME"

        fun newInstance(name: String): PokemonSearchFragment{
            val args = Bundle()
            args.putString(NAME,name)

            val fragment = PokemonSearchFragment()
            fragment.arguments = args
            return fragment
        }
    }
}