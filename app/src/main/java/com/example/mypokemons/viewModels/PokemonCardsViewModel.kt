package com.example.mypokemons.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.PokemonsCardsModel
import com.example.domain.models.model.BasePokemonModel
import com.example.mypokemons.ui.BaseApplication
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PokemonCardsViewModel(application: Application) : AndroidViewModel(application) {
    val pokemonsLiveData = MutableLiveData<List<BasePokemonModel>>()

    private val appComponent = (application as BaseApplication).getAppComponent()
    private var model = PokemonsCardsModel(appComponent)

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun setPreviewPokemons() {
        val disposeSub = model.getPreviewRetrofitCards()
            .subscribeOn(Schedulers.io())
            .flatMap {
                model.refreshTable(it.pokemons)
            }
            .map {
                var pokemonsList = listOf<BasePokemonModel>()
                if (it.isNotEmpty())
                    pokemonsList = BasePokemonModel.cast(it) { pokemonInfo ->
                        BasePokemonModel(
                            pokemonInfo.name,
                            pokemonInfo.images.small,
                            pokemonInfo.isFavorite
                        )
                    }
                return@map pokemonsList
            }
            .onErrorResumeNext {
                Log.e("AllCardsError", it.message.toString())
                return@onErrorResumeNext model.getPreviewRoomCards()
                    .map { dbPokemons ->
                        var pokemonsList = listOf<BasePokemonModel>()
                        if (dbPokemons.isNotEmpty())
                            pokemonsList = BasePokemonModel.cast(dbPokemons) { pokemonInfo ->
                                BasePokemonModel(
                                    pokemonInfo.name,
                                    pokemonInfo.images.small,
                                    pokemonInfo.isFavorite
                                )
                            }
                        return@map pokemonsList
                    }
            }
            .subscribe({
                pokemonsLiveData.postValue(it)
            }, {
                Log.d("ErrorDb", it.stackTraceToString())
            }
            )

        compositeDisposable.add(disposeSub)
    }
}
