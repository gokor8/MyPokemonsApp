package com.example.mypokemons.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mypokemons.R
import com.example.mypokemons.data.database.room.PokemonsDatabase
import com.example.mypokemons.ui.adapters.MainRecycleViewAdapter
import com.example.mypokemons.databinding.FragmentPokemonCardsBinding
import com.example.mypokemons.view_models.PokemonCardsViewModel
import com.example.mypokemons.view_models.RvDiffCallback
import com.squareup.picasso.Picasso

class PokemonCardsFragment : Fragment(R.layout.fragment_pokemon_cards) {

    private var viewBinding: FragmentPokemonCardsBinding? = null

    private lateinit var viewModel: PokemonCardsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentPokemonCardsBinding.bind(view)
        viewModel = ViewModelProvider(this).get(PokemonCardsViewModel::class.java)

        val rvAdapter = MainRecycleViewAdapter()

        viewBinding?.recycleView?.run {
            layoutManager = GridLayoutManager(this.context, 2)
            adapter = rvAdapter
        }

        viewModel.pokemonsLiveData.observe(this as LifecycleOwner, Observer {

            viewBinding?.pbWait?.visibility = View.INVISIBLE

            if (it == null || it.pokemons.isEmpty())
                viewBinding?.tvNotify?.visibility = View.VISIBLE
            else {
                viewBinding?.tvNotify?.visibility = View.INVISIBLE

                rvAdapter.refreshAdapter(it.pokemons)
            }
        })

        viewModel.getAllPokemons()
        //var dataBaseInstance = this.context?.let { it1 -> PokemonsDatabase.getDatabase(it1) }
        //viewModel.setInstanceOfDb(dataBaseInstance!!)
        //viewModel.getInsert()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}