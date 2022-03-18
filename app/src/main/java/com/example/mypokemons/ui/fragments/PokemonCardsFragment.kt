package com.example.mypokemons.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mypokemons.R
import com.example.mypokemons.ui.adapters.MainRecycleViewAdapter
import com.example.mypokemons.databinding.FragmentPokemonCardsBinding
import com.example.mypokemons.view_models.PokemonCardsViewModel
import com.example.mypokemons.view_models.RvDiffCallback

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

        viewModel.pokemonsLiveData.observe(this as LifecycleOwner, Observer {

            if (it == null || it.data.isEmpty())
                viewBinding?.tvNotify?.visibility = View.VISIBLE
            else {
                viewBinding?.tvNotify?.visibility = View.INVISIBLE

                myAdapter?.refreshAdapter(it.data)
                //viewBinding?.recycleView?.adapter
                //= MainRecycleViewAdapter(this.requireContext(), it.data.toMutableList())
            }
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllPokemons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}