package com.example.mypokemons.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mypokemons.PokemonCards
import com.example.mypokemons.data.PokemonBaseInfo

class PokemonCardsViewModel : ViewModel(){
    val pokemonsLiveData = MutableLiveData<ArrayList<PokemonBaseInfo>>()
    val pokemons = ArrayList<PokemonBaseInfo>()
    private var number: Int = 0

    fun getAllPokemons(){
        if(number <= 0) {
            pokemons.add(PokemonBaseInfo("Oleg", "SuperCool"))
            pokemons.add(PokemonBaseInfo("Denisich", "SuperCool"))
            pokemons.add(PokemonBaseInfo("Vlad", "Выполнил работу Влад Щепетьев"))
            pokemons.add(PokemonBaseInfo("gRiShA", "Ереванский цыган в сети"))

            //if(newList > pokemons)
            pokemonsLiveData.value = pokemons
            number++
        }
    }
}