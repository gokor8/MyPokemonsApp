package com.example.domain.models

import com.example.mypokemons.data.api_services.ApiService
import com.example.mypokemons.data.storage.PokemonModel
import io.reactivex.Single

class SearchPokemonsModel(val apiService: ApiService) {

    fun getFoundCards(name: String): Single<PokemonModel> = apiService.getFoundCards("name:$name")
}