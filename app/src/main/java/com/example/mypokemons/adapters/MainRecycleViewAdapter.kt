package com.example.mypokemons.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokemons.R
import com.example.mypokemons.data.PokemonBaseInfo
import com.example.mypokemons.databinding.RecycleItemBinding
import com.example.mypokemons.view_models.PokemonCardsViewModel

class MainRecycleViewAdapter(
    val context: Context
) : RecyclerView.Adapter<MainRecycleViewAdapter.PokemonsViewHolder>(){

    var pokemons = mutableListOf<PokemonBaseInfo>()
    fun setList(movies: List<PokemonBaseInfo>) {
        this.pokemons = movies.toMutableList()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MainRecycleViewAdapter.PokemonsViewHolder {
        val bindingRoot = RecycleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //val root = LayoutInflater.from(parent.context).inflate(R.layout.recycle_item,parent,false)
        return PokemonsViewHolder(bindingRoot)
    }

    override fun onBindViewHolder(holder: MainRecycleViewAdapter.PokemonsViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    override fun getItemCount(): Int {
        if(pokemons.size==0){
            Toast.makeText(context,"List is empty", Toast.LENGTH_LONG).show()
        }
        return pokemons.size
    }


    inner class PokemonsViewHolder(private val binding: RecycleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(blog: PokemonBaseInfo){
            binding.titleName.text = blog.name
            binding.secondName.text = blog.rare
            /*binding.showInfo.setOnClickListener {
                viewModel.remove(blog)
                notifyItemRemoved(arrayList.indexOf(blog))
            }*/
        }

    }

}