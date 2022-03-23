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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
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
                    Log.d("Name", "$name : ${rPokemon.name}")
                    model.getById(rPokemon.id)
                }
                .subscribe({ pokemon ->
                    if(pokemon.id != "-1")
                        filteredPokemons.add(pokemon)
                    pokemonsLiveData.postValue(filteredPokemons)
                }, {
                    pokemonsLiveData.postValue(filteredPokemons)
                    Log.d("CardsError", it.message.toString())
                })
        )
    }
}