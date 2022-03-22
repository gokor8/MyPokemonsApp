package com.example.domain.models

import android.annotation.SuppressLint
import android.util.Log
import com.example.mypokemons.data.database.room.PokemonEntity
import com.example.mypokemons.data.database.room.models.Images
import com.example.mypokemons.data.di.AppComponent
import com.example.mypokemons.data.storage.PokemonInfo
import com.example.mypokemons.data.storage.PokemonModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.lang.Exception


class PokemonsCardsModel(val appComponent: AppComponent) {

    private val pokemonsDao = appComponent.getDao()
    private val apiService = appComponent.getApiService()

    fun getPreviewRetrofitCards(): Single<PokemonModel> = apiService.getAllCards()

    fun getPreviewRoomCards(): Single<List<PokemonEntity>> = pokemonsDao.getAll()

    fun refreshTable(insertData: List<PokemonInfo>) =
        pokemonsDao.getAll()
            .map { dbPokemons ->
                if(insertData.isEmpty())
                    return@map dbPokemons

                pokemonsDao.deleteAll()

                val dbList = insertData.mapIndexed { index,pokemon ->
                    var isFavorite = false
                    if(dbPokemons.isNotEmpty() && dbPokemons.size >= index)
                        isFavorite = dbPokemons[index].isFavorite

                    var attack = "Nope"
                    if(pokemon.attacks != null)
                        attack = pokemon.attacks[0].name

                        PokemonEntity(
                            pokemon.name,
                            pokemon.rarity ?: "",
                            pokemon.types[0] ?: "",
                            pokemon.subtypes[0] ?: "",
                            pokemon.hp,
                            Images(pokemon.images.small, pokemon.images.large),
                            attack?: "",
                            isFavorite
                        )
                }
                pokemonsDao.insertList(dbList)

                return@map dbList
            }
}