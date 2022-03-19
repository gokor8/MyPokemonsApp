package com.example.mypokemons.view_models

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.AllPokemonsCardsModel
import com.example.mypokemons.data.database.room.PokemonEntity
import com.example.mypokemons.data.database.room.PokemonsDatabase
import com.example.mypokemons.data.storage.PokemonModel
import io.reactivex.schedulers.Schedulers
import okhttp3.internal.notify

class PokemonCardsViewModel() : ViewModel() {
    val pokemonsLiveData = MutableLiveData<PokemonModel>()

    private var model = AllPokemonsCardsModel()
    private var dataBaseInstance: PokemonsDatabase? = null

    fun setInstanceOfDb(dataBaseInstance: PokemonsDatabase) {
        this.dataBaseInstance = dataBaseInstance
    }

    @SuppressLint("CheckResult")
    fun getAllPokemons() {
        model.getAllCards()
            .subscribeOn(Schedulers.io())
            .subscribe(
                { onNext: PokemonModel ->
                    if (onNext.pokemons.size != pokemonsLiveData.value?.pokemons?.size)
                        pokemonsLiveData.postValue(onNext)
                },
                { onError ->
                    Log.d("OnErrorRx", "Error")
                    pokemonsLiveData.notify()
                }
            )
    }

    fun getInsert() {
        dataBaseInstance?.pokemonDao()?.insertList(
            listOf(
                PokemonEntity(
                    1, "AA", "", "", "", "", ""
                )
            )
        )?.subscribeOn(Schedulers.io())
            ?.subscribe({
            }, {

            })?.let {
            }
        val result = dataBaseInstance?.pokemonDao()?.getAll()
    }
}
