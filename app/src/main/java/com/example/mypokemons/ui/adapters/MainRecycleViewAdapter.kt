package com.example.mypokemons.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokemons.data.storage.Data
import com.example.mypokemons.databinding.RecycleItemBinding
import com.example.mypokemons.view_models.RvDiffCallback

class MainRecycleViewAdapter(
    val context: Context,
    private val pokemons: MutableList<Data> = mutableListOf()
) : RecyclerView.Adapter<MainRecycleViewAdapter.PokemonsViewHolder>() {

    fun refreshAdapter(
        newPokemons: List<Data>,
    ) {
        val diffCallback = RvDiffCallback(pokemons, newPokemons)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        pokemons.clear()
        pokemons.addAll(newPokemons)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MainRecycleViewAdapter.PokemonsViewHolder {
        val bindingRoot =
            RecycleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonsViewHolder(bindingRoot)
    }

    override fun onBindViewHolder(
        holder: MainRecycleViewAdapter.PokemonsViewHolder,
        position: Int
    ) {
        holder.bind(pokemons[position])
    }

    override fun getItemCount(): Int {
        if (pokemons.size == 0) {
            Toast.makeText(context, "List is empty", Toast.LENGTH_LONG).show()
        }
        return pokemons.size
    }

    inner class PokemonsViewHolder(private val binding: RecycleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(blog: Data) {
            binding.titleName.text = blog.name
            binding.secondName.text = blog.id
            /*binding.showInfo.setOnClickListener {
                viewModel.remove(blog)
                notifyItemRemoved(arrayList.indexOf(blog))
            }*/
        }
    }


}