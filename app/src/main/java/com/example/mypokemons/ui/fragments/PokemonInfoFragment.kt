package com.example.mypokemons.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mypokemons.R
import com.example.mypokemons.databinding.FragmentPokemonInfoBinding
import com.example.mypokemons.viewModels.PokemonInfoViewModel
import com.squareup.picasso.Picasso

class PokemonInfoFragment(val id: String) : Fragment(R.layout.fragment_pokemon_info) {

    private var binding: FragmentPokemonInfoBinding? = null
    private var viewModel: PokemonInfoViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonInfoBinding.bind(view)
        viewModel = ViewModelProvider(this)[PokemonInfoViewModel::class.java]

        binding?.run {
            viewModel?.let {
                it.infoLiveData.observe(this@PokemonInfoFragment as LifecycleOwner) { pokemonInfo ->
                    Picasso.get().load(pokemonInfo.image).into(image)
                    titleName.text = pokemonInfo.name
                    species.text = "${pokemonInfo.type} ${pokemonInfo.subtype} ${pokemonInfo.rare}"
                    description.text =
                        "Hp: ${pokemonInfo.health} Type Attack: ${pokemonInfo.typeAttack}"
                    changeFab(pokemonInfo.isFavorite)
                }
                it.setPreviewData(id)

                fab.setOnClickListener { fab ->
                    it.infoLiveData.value?.apply {
                        it.updateFavorite(id,isFavorite)
                    }
                }
            }
        }
    }

    private fun changeFab(isFavorite: Boolean) {
        var defaultImage = R.drawable.ic_empty_star
        if (isFavorite)
            defaultImage = R.drawable.ic_favorite_star
        binding?.fab?.setImageResource(defaultImage)
    }
}