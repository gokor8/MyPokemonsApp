package com.example.domain.models

import com.example.mypokemons.data.api_services.ApiService
import com.example.mypokemons.data.storage.PokemonModel
import io.reactivex.Single

class SearchPokemonsModel {
    private val retrofitService = ApiService.apiService

    fun getFoundCards(name: String): Single<PokemonModel> = retrofitService.getFoundCards(name)
}