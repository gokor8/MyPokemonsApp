package com.example.mypokemons.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokemons.data.PokemonBaseInfo
import com.example.mypokemons.data.models.PokemonsModel
import com.example.mypokemons.data.storage.PokemonsJson
import kotlinx.coroutines.launch
import retrofit2.Response

class PokemonCardsViewModel : ViewModel(){
    val pokemonsLiveData = MutableLiveData<Response<PokemonsJson>>()

    val pokemons = ArrayList<PokemonBaseInfo>()
    var model = PokemonsModel()

    fun getAllPokemons(){
//        if(number <= 0) {
//            pokemons.add(PokemonBaseInfo("Oleg", "SuperCool"))
//            pokemons.add(PokemonBaseInfo("Denisich", "SuperCool"))
//            pokemons.add(PokemonBaseInfo("Vlad", "Выполнил работу Влад Щепетьев"))
//            pokemons.add(PokemonBaseInfo("gRiShA", "Ереванский цыган в сети"))
//
//            //if(newList > pokemons)
//            pokemonsLiveData.value = pokemons
//            number++
//        }
        viewModelScope.launch {
            pokemonsLiveData.value = model.getAllCards()
        }
    }
}