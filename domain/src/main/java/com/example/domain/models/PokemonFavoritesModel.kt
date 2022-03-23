package com.example.domain.models

import com.example.mypokemons.data.database.room.dao.PokemonDao
import com.example.mypokemons.data.di.AppComponent

class PokemonFavoritesModel(appComponent: AppComponent) {

    private val pokemonsDao = appComponent.getDao()
    private val apiService = appComponent.getApiService()

    fun getFavoritesCards() = pokemonsDao.getCardsByFavorite(PokemonDao.FAVORITE)
}