package com.example.mypokemons.fragments

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mypokemons.R
import com.example.mypokemons.adapters.MainRecycleViewAdapter
import com.example.mypokemons.databinding.FragmentPokemonCardsBinding
import com.example.mypokemons.view_models.PokemonCardsViewModel

class PokemonCardsFragment : Fragment(R.layout.fragment_pokemon_cards) {

    private var viewBinding: FragmentPokemonCardsBinding? = null

    private lateinit var viewModel: PokemonCardsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentPokemonCardsBinding.bind(view)
        viewModel = ViewModelProvider(this).get(PokemonCardsViewModel::class.java)

        val myAdapter = this.context?.let { MainRecycleViewAdapter(it) }

        viewBinding?.recycleView?.run {
            layoutManager = GridLayoutManager(this.context, 2)
            adapter = myAdapter
        }

        viewModel.pokemonsLiveData.observe(this as LifecycleOwner, Observer{
            it.body()?.let { it1 -> myAdapter?.setList(it1) }
        })

        viewModel.getAllPokemons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}