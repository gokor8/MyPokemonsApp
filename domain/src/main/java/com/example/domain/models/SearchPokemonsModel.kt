package com.example.domain.models

import com.example.mypokemons.data.di.AppComponent

class SearchPokemonsModel(val appComponent: AppComponent) {

    private val pokemonsDao = appComponent.getDao()
    private val apiService = appComponent.getApiService()

    fun getFoundCards(name: String) = apiService.getFoundCards("name:$name")

    fun getFoundDbCards(name: String) = pokemonsDao.getByName(name)

}