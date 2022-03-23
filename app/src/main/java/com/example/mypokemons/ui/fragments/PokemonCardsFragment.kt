package com.example.mypokemons.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mypokemons.R
import com.example.mypokemons.databinding.FragmentPokemonCardsBinding
import com.example.mypokemons.ui.adapters.MainRecycleViewAdapter
import com.example.mypokemons.viewModels.BaseViewModel

class PokemonCardsFragment() :
    Fragment(R.layout.fragment_pokemon_cards) {

    private var viewModel: BaseViewModel? = null

    constructor(viewModel: BaseViewModel) : this() {
        this.viewModel = viewModel
    }

    private var binding: FragmentPokemonCardsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPokemonCardsBinding.bind(view)

        viewModel?.let { vm ->
            viewModel = ViewModelProvider(this)[vm::class.java]
            binding?.apply {
                val rvAdapter = MainRecycleViewAdapter(parentFragmentManager)

                recycleView.apply {
                    layoutManager = GridLayoutManager(this.context, 2)
                    adapter = rvAdapter
                }

                vm.pokemonsLiveData.observe(this@PokemonCardsFragment as LifecycleOwner, Observer {
                    pbWait.visibility = View.INVISIBLE

                    if (it == null || it.isEmpty())
                        tvNotify.visibility = View.VISIBLE
                    else {
                        tvNotify.visibility = View.INVISIBLE
                        rvAdapter.refreshAdapter(it)
                    }
                })
                vm.updateData()
            }
        }
    }
}