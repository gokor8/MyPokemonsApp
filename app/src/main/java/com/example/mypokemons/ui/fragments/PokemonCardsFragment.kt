package com.example.mypokemons.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mypokemons.R
import com.example.mypokemons.ui.adapters.MainRecycleViewAdapter
import com.example.mypokemons.databinding.FragmentPokemonCardsBinding
import com.example.mypokemons.viewModels.PokemonCardsViewModel

class PokemonCardsFragment() : Fragment(R.layout.fragment_pokemon_cards) {

    private lateinit var binding: FragmentPokemonCardsBinding

    private lateinit var viewModel: PokemonCardsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonCardsBinding.bind(view)
        viewModel = ViewModelProvider(this)[PokemonCardsViewModel::class.java]

        val rvAdapter = MainRecycleViewAdapter()

        binding.recycleView.run {
            layoutManager = GridLayoutManager(this.context, 2)
            adapter = rvAdapter
        }

        viewModel.pokemonsLiveData.observe(this as LifecycleOwner, Observer {

            binding.pbWait.visibility = View.INVISIBLE

            if (it == null || it.isEmpty())
                binding.tvNotify.visibility = View.VISIBLE
            else {
                binding.tvNotify.visibility = View.INVISIBLE

                rvAdapter.refreshAdapter(it)
            }
        })

        viewModel.getPreviewPokemons()
    }

}