package com.example.mypokemons.viewModels

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.SearchPokemonsModel
import com.example.domain.models.models.BasePokemonModel
import com.example.mypokemons.ui.BaseApplication
import com.example.mypokemons.ui.fragments.PokemonCardsFragment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class SearchPokemonCardsViewModel(application: Application) : BaseViewModel(application) {

    private val model = SearchPokemonsModel(appComponent)

    override fun updateData() {
        updateDataByName("")
    }

    fun updateDataByName(name: String) {
        val pokemons = mutableListOf<BasePokemonModel>()
        compositeDisposable.add(
            model.getWebCards(name)
                .subscribeOn(Schedulers.io())
                .flattenAsObservable { it.pokemons }
                .flatMap { rPokemon ->
                    Log.d("Name", "$name : ${rPokemon.name}")
                    model.getById(rPokemon.id)
                }
                .subscribe({ pokemon ->
                    if(pokemon.id != "-1")
                        pokemons.add(pokemon)
                    pokemonsLiveData.postValue(pokemons)
                }, {
                    pokemonsLiveData.postValue(pokemons)
                    Log.d("CardsError", it.message.toString())
                },{
                    pokemonsLiveData.postValue(pokemons)
                })
        )
    }
}