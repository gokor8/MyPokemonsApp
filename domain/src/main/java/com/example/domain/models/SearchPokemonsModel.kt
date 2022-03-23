package com.example.domain.models

import com.example.domain.models.models.BasePokemonModel
import com.example.mypokemons.data.di.AppComponent
import com.example.mypokemons.data.api_services.gson_models.PokemonInfo
import io.reactivex.Observable

class SearchPokemonsModel(val appComponent: AppComponent) {

    private val pokemonsDao = appComponent.getDao()
    private val apiService = appComponent.getApiService()

    fun getWebCards(name: String) = apiService.getByNameCards("name:$name")

    fun getAllFilteredDbCards(name: String, pokemonInfo: PokemonInfo): Observable<BasePokemonModel> {
        return pokemonsDao.getByName(name).map { dbPokemon ->
            if (dbPokemon.isEmpty())
                return@map BasePokemonModel(
                    pokemonInfo.name,
                    pokemonInfo.images.small,
                    false
                )

            return@map BasePokemonModel(
                pokemonInfo.name,
                pokemonInfo.images.small,
                dbPokemon[0].isFavorite
            )
        }
    }

}