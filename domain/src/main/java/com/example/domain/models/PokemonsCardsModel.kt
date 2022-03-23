package com.example.domain.models

import com.example.domain.models.models.BasePokemonModel
import com.example.mypokemons.data.database.room.PokemonEntity
import com.example.mypokemons.data.database.room.models.Images
import com.example.mypokemons.data.di.AppComponent
import com.example.mypokemons.data.api_services.gson_models.PokemonInfo
import com.example.mypokemons.data.api_services.gson_models.PokemonModel
import io.reactivex.Single


open class PokemonsCardsModel(val appComponent: AppComponent) {

    protected val pokemonsDao = appComponent.getDao()
    protected val apiService = appComponent.getApiService()

    fun getWebCards(): Single<PokemonModel> = apiService.getAllCards()

    fun getDbCards(): Single<List<BasePokemonModel>> {
        return pokemonsDao.getAll().map { dbPokemons ->
            var pokemonsList = listOf<BasePokemonModel>()
            if (dbPokemons.isNotEmpty())
                pokemonsList = BasePokemonModel.cast(dbPokemons) { pokemonInfo ->
                    BasePokemonModel(
                        pokemonInfo.pId,
                        pokemonInfo.name,
                        pokemonInfo.images.small,
                        pokemonInfo.isFavorite
                    )
                }
            return@map pokemonsList
        }
    }

    fun castDbCards(dbPokemons: List<PokemonEntity>): List<BasePokemonModel> {
        var pokemonsList = listOf<BasePokemonModel>()
        if (dbPokemons.isNotEmpty())
            pokemonsList = BasePokemonModel.cast(dbPokemons) { pokemonInfo ->
                BasePokemonModel(
                    pokemonInfo.pId,
                    pokemonInfo.name,
                    pokemonInfo.images.small,
                    pokemonInfo.isFavorite
                )
            }
        return pokemonsList
    }

    fun syncDbCards(insertData: List<PokemonInfo>) =
        pokemonsDao.getAll()
            .map { dbPokemons ->
                if (insertData.isEmpty())
                    return@map dbPokemons

                pokemonsDao.deleteAll()

                val dbList = insertData.mapIndexed { index, pokemon ->
                    var isFavorite = false
                    if (dbPokemons.isNotEmpty() && dbPokemons.size >= index)
                        isFavorite = dbPokemons[index].isFavorite

                    var attack = "Nope"
                    if (pokemon.attacks != null) attack = pokemon.attacks[0].name

                    PokemonEntity(
                        pokemon.id,
                        pokemon.name,
                        pokemon.rarity ?: "",
                        pokemon.types[0] ?: "",
                        pokemon.subtypes[0] ?: "",
                        pokemon.hp,
                        Images(pokemon.images.small, pokemon.images.large),
                        attack ?: "",
                        isFavorite
                    )
                }
                pokemonsDao.insertList(dbList)

                return@map dbList
            }
}