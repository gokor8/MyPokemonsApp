package com.example.domain.models

import com.example.domain.models.model.BasePokemonModel
import com.example.mypokemons.data.apiServices.ApiService
import io.reactivex.Observable

class SearchPokemonsModel(val apiService: ApiService) {

    fun getFoundCards(name: String): Observable<List<BasePokemonModel>> = Observable.just(listOf(BasePokemonModel("a","a")))//apiService.getFoundCards("name:$name")
}