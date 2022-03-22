package com.example.domain.models

import android.util.Log
import com.example.mypokemons.data.database.room.PokemonEntity
import com.example.mypokemons.data.di.AppComponent
import io.reactivex.Observable

class PokemonInfoModel(val appComponent: AppComponent) {

    private val pokemonsDao = appComponent.getDao()
    private val apiService = appComponent.getApiService()

    fun getCardInfo(name: String) = pokemonsDao.getEqualsByName(name)

    fun getCardByName(name: String) = pokemonsDao.getCardByName(name)

    fun updateFavorite(pokemon: PokemonEntity): Observable<PokemonEntity> {
        Log.d("RefreshFavorite", pokemon.isFavorite.toString())
        pokemonsDao.update(pokemon)

        return pokemonsDao.getFavoriteByName(pokemon.name)
    }
}