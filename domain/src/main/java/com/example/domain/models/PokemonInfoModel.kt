package com.example.domain.models

import android.util.Log
import com.example.mypokemons.data.database.room.PokemonEntity
import com.example.di.AppComponent
import io.reactivex.Observable
import io.reactivex.Single

class PokemonInfoModel(val appComponent: AppComponent) {

    private val pokemonsDao = appComponent.getDao()
    private val apiService = appComponent.getApiService()

    fun getCardById(id: String) = pokemonsDao.getCardByRawId(id)

    fun updateFavorite(pokemon: PokemonEntity){
        Log.d("RefreshFavorite", pokemon.isFavorite.toString())
        return pokemonsDao.update(pokemon)
    }
}