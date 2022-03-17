package com.example.mypokemons.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokemons.data.storage.Data
import com.example.mypokemons.data.storage.PokemonsJson
import com.example.mypokemons.databinding.RecycleItemBinding

class MainRecycleViewAdapter(
    val context: Context
) : RecyclerView.Adapter<MainRecycleViewAdapter.PokemonsViewHolder>(){

    var pokemons = mutableListOf<Data>()
    fun setList(dataJson: PokemonsJson) {
        this.pokemons = dataJson.data.toMutableList()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MainRecycleViewAdapter.PokemonsViewHolder {
        val bindingRoot = RecycleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        fun bind(blog: Data){
            binding.titleName.text = blog.name
            binding.secondName.text = blog.id
            /*binding.showInfo.setOnClickListener {
                viewModel.remove(blog)
                notifyItemRemoved(arrayList.indexOf(blog))
            }*/
        }

    }

}