package com.example.domain.models

import com.example.domain.models.models.BasePokemonModel
import com.example.di.AppComponent
import io.reactivex.Observable

class SearchPokemonsModel(val appComponent: AppComponent) {

    private val pokemonsDao = appComponent.getDao()
    private val apiService = appComponent.getApiService()

    fun getWebCards(name: String) = apiService.getByNameCards("name:$name")

    fun getById(id: String): Observable<BasePokemonModel> = pokemonsDao.getCardsByRawId(id)
        .map { dbPokemon ->
            if (dbPokemon.isNotEmpty())
                return@map dbPokemon[0].run {
                    BasePokemonModel(
                        pId,
                        name,
                        images.small,
                        isFavorite
                    )
                }
            else
                return@map BasePokemonModel("-1", "", "", false)
        }
}