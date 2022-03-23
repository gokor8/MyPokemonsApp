package com.example.mypokemons.viewModels

import android.app.Application
import android.util.Log
import com.example.domain.models.SearchPokemonsModel
import com.example.domain.models.models.BasePokemonModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchPokemonCardsViewModel(application: Application, private val name: String) : BaseViewModel(application) {

    constructor(application: Application) : this(application, "")

    private val model = SearchPokemonsModel(appComponent)

    override fun updateData() {
        val filteredPokemons = mutableListOf<BasePokemonModel>()
        compositeDisposable.add(
            model.getWebCards(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable { it.pokemons }
                .flatMap { rPokemon ->
                    model.getAllFilteredDbCards(name, rPokemon)
                }
                .subscribe({ pokemon ->
                    filteredPokemons.add(pokemon)
                    pokemonsLiveData.postValue(filteredPokemons)
                }, {
                    Log.d("CardsError", it.message.toString())
                })
        )
    }
}