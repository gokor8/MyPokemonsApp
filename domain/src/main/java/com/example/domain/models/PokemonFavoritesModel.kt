package com.example.domain.models

import com.example.mypokemons.data.database.room.dao.PokemonDao
import com.example.mypokemons.data.di.AppComponent

class PokemonFavoritesModel(appComponent: AppComponent): PokemonsCardsModel(appComponent) {

    fun getFavoritesCards() = pokemonsDao.getCardsByFavorite(PokemonDao.FAVORITE)
}