package com.example.domain.models

import android.util.Log
import com.example.mypokemons.data.database.room.PokemonEntity
import com.example.di.AppComponent
import io.reactivex.Observable

class PokemonInfoModel(val appComponent: AppComponent) {

    private val pokemonsDao = appComponent.getDao()
    private val apiService = appComponent.getApiService()

    fun getCardInfo(id: String) = pokemonsDao.getCardByRawId(id)

    fun updateFavorite(pokemon: PokemonEntity): Observable<PokemonEntity> {
        Log.d("RefreshFavorite", pokemon.isFavorite.toString())
        pokemonsDao.update(pokemon)

        return pokemonsDao.getCardByRawId(pokemon.pId)
    }
}