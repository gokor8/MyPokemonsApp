package com.example.mypokemons.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.SearchPokemonsModel
import com.example.domain.models.model.BasePokemonModel
import com.example.mypokemons.data.database.room.PokemonEntity
import com.example.mypokemons.data.storage.PokemonModel
import com.example.mypokemons.ui.BaseApplication
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchPokemonCardsViewModel(application: Application) : AndroidViewModel(application) {
    val foundPokemonsLiveData = MutableLiveData<List<BasePokemonModel>>()

    private val appComponent = (application as BaseApplication).getAppComponent()
    private val model = SearchPokemonsModel(appComponent)

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun search(name: String) {
        val filteredPokemons = mutableListOf<BasePokemonModel>()
        compositeDisposable.add(
            model.getFoundCards(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable { it.pokemons }
                .flatMap { rPokemon ->
                    model.getFoundDbCards(rPokemon.name).map { dbPokemon ->
                        if (dbPokemon.isEmpty())
                            return@map BasePokemonModel(
                                rPokemon.name,
                                rPokemon.images.small,
                                false
                            )

                            return@map BasePokemonModel(
                                rPokemon.name,
                                rPokemon.images.small,
                                dbPokemon[0].isFavorite
                            )
                    }
                }
                .subscribe({ pokemon ->
                    filteredPokemons.add(pokemon)
                    foundPokemonsLiveData.postValue(filteredPokemons)
                }, {
                    Log.d("ThreadError", it.message.toString())
                })
        )
    }
}