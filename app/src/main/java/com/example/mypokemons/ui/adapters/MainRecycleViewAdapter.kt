package com.example.mypokemons.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.model.BasePokemonModel
import com.example.mypokemons.R
import com.example.mypokemons.databinding.ElRecyclerViewCardBinding
import com.example.mypokemons.ui.callback.RvDiffCallback
import com.example.mypokemons.ui.fragments.PokemonInfoFragment
import com.squareup.picasso.Picasso

class MainRecycleViewAdapter(
    private val supportFragmentManager: FragmentManager,
    private val pokemons: MutableList<BasePokemonModel> = mutableListOf(),
) : RecyclerView.Adapter<MainRecycleViewAdapter.PokemonsViewHolder>() {

    fun refreshAdapter(
        newPokemons: List<BasePokemonModel>,
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
            ElRecyclerViewCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class PokemonsViewHolder(private val binding: ElRecyclerViewCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: BasePokemonModel) {

            binding.titleName.text = pokemon.name
            Picasso.get().load(pokemon.image).into(binding.showInfo)

            var starId = R.drawable.ic_baseline_star
            if(pokemon.isFavorite)
                starId = R.drawable.ic_favorite_star

            binding.favorite.setImageResource(starId)

            binding.llMain.setOnClickListener {
                supportFragmentManager.commit {
                    replace(R.id.fragmentContainerView, PokemonInfoFragment(pokemon.name))
                    setReorderingAllowed(true)
                }
            }
        }
    }


}