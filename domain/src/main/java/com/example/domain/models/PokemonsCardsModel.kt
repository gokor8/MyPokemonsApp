package com.example.domain.models

import com.example.mypokemons.data.database.room.PokemonEntity
import com.example.mypokemons.data.database.room.models.Images
import com.example.mypokemons.data.di.AppComponent
import com.example.mypokemons.data.storage.PokemonInfo
import com.example.mypokemons.data.storage.PokemonModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class PokemonsCardsModel(val appComponent: AppComponent) {

    private val pokemonsDao = appComponent.getDao()
    private val apiService = appComponent.getApiService()

    fun getPreviewRetrofitCards(): Single<PokemonModel> = apiService.getAllCards()

    fun getPreviewRoomCards(): Single<List<PokemonEntity>> = pokemonsDao.getAll()

    fun refreshTable(insertData: List<PokemonInfo>) {
        pokemonsDao.getSize()
            .subscribeOn(Schedulers.io())
            .subscribe { count ->
                //if (count > 0)
                    pokemonsDao.deleteAll()

                val dbList = insertData.map {
                    PokemonEntity(
                        0,
                        it.name,
                        "",
                        "it.types.first()",
                        "it.subtypes.first()",
                        it.hp,
                        Images(it.images.small, it.images.large),
                        "it.attacks.first().name"
                    )
                }
                pokemonsDao.insertList(dbList)
            }
    }
    /*{
        var pokemonsList: List<BasePokemonModel> = listOf()
        apiService.getAllCards()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
           .subscribe {
               if(it.isSuccessful)
               pokemonsList = BasePokemonModel.cast(it.body().pokemons){ pokemonInfo ->
                   BasePokemonModel(pokemonInfo.name, pokemonInfo.images.small)
               }
               else{
                   pokemonsList = BasePokemonModel.cast(pokemonsDao.getAll()){pokemonInfo ->
                       BasePokemonModel(pokemonInfo.name, pokemonInfo.images.small)
                   }
               }
           }
        return Observable.just(pokemonsList)
    }*/

}