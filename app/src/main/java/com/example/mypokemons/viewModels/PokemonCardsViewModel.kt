package com.example.mypokemons.viewModels

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.PokemonsCardsModel
import com.example.domain.models.model.BasePokemonModel
import com.example.mypokemons.data.database.room.PokemonsDatabase
import com.example.mypokemons.data.database.room.dao.PokemonDao
import com.example.mypokemons.ui.BaseApplication
import io.reactivex.schedulers.Schedulers

class PokemonCardsViewModel(application: Application) : AndroidViewModel(application) {
    val pokemonsLiveData = MutableLiveData<List<BasePokemonModel>>()

    val appComponent = (application as BaseApplication).getAppComponent()

    val dbDao: PokemonDao = appComponent.getDao()

    private var model = PokemonsCardsModel(appComponent)
    private var dataBaseInstance: PokemonsDatabase? = null

    @SuppressLint("CheckResult")
    fun getPreviewPokemons() {
        model.getPreviewRetrofitCards()
            .subscribeOn(Schedulers.io())
            .subscribe({ onNext ->
                model.refreshTable(onNext.pokemons)
                if (onNext.pokemons.size != pokemonsLiveData.value?.size) {
                    val pokemonsList = BasePokemonModel.cast(onNext.pokemons) { pokemonInfo ->
                        BasePokemonModel(pokemonInfo.name, pokemonInfo.images.small)
                    }
                    pokemonsLiveData.postValue(pokemonsList)
                }
            }, {
                model.getPreviewRoomCards()
                    .subscribe({ dbPokemons ->
                        var pokemonsList = listOf<BasePokemonModel>()
                        if (dbPokemons.isNotEmpty())
                            pokemonsList = BasePokemonModel.cast(dbPokemons) { pokemonInfo ->
                                BasePokemonModel(pokemonInfo.name, pokemonInfo.images.small)
                            }
                        pokemonsLiveData.postValue(pokemonsList)
                    },
                        {
                            Log.d("ErrorDb", it.stackTraceToString())
                        })
            }
            )
    }

    fun getInsert() {
        /*dbDao.insertList(
            listOf(
                PokemonEntity(
                    3, "AA2", "", "", "", "", ""
                ),
                PokemonEntity(
                    2, "AA1", "", "", "", "", ""
                )
            )
        ).subscribeOn(Schedulers.io())

        dbDao.getAll()
            .subscribe {
                Log.d("DbData", it.joinToString(separator = "\r\n"))
            }*/
    }
}
