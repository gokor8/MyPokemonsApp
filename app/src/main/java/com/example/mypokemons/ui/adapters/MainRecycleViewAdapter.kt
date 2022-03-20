package com.example.mypokemons.ui.adapters

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokemons.R
import com.example.mypokemons.data.storage.PokemonInfo
import com.example.mypokemons.databinding.RvCardBinding
import com.example.mypokemons.view_models.RvDiffCallback
import com.squareup.picasso.Picasso

class MainRecycleViewAdapter(
    private val pokemons: MutableList<PokemonInfo> = mutableListOf(),
) : RecyclerView.Adapter<MainRecycleViewAdapter.PokemonsViewHolder>() {

    fun refreshAdapter(
        newPokemons: List<PokemonInfo>,
    ) {
        val diffResult = DiffUtil.calculateDiff(
            RvDiffCallback(pokemons, newPokemons)
        )
        pokemons.clear()
        pokemons.addAll(newPokemons)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MainRecycleViewAdapter.PokemonsViewHolder {
        val bindingRoot =
            RvCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonsViewHolder(bindingRoot)
    }

    override fun onBindViewHolder(
        holder: MainRecycleViewAdapter.PokemonsViewHolder,
        position: Int
    ) {
        holder.bind(pokemons[position])
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    inner class PokemonsViewHolder(private val binding: RvCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokemonInfo) {
            binding.titleName.text = pokemon.name

            Picasso.get().load(pokemon.images.large).into(binding.showInfo)
            binding.llMain.setOnClickListener {
                //navController.navigate(R.id.action_mainFragment_to_pocemonInfo)
            }
        }
    }


}