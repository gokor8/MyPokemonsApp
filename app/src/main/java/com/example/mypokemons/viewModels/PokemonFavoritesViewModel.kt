package com.example.mypokemons.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.PokemonFavoritesModel
import com.example.domain.models.model.BasePokemonModel
import com.example.mypokemons.ui.BaseApplication
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PokemonFavoritesViewModel(application: Application) : AndroidViewModel(application) {
    val pokemonsLiveData = MutableLiveData<List<BasePokemonModel>>()

    private val appComponent = (application as BaseApplication).getAppComponent()
    private var model = PokemonFavoritesModel(appComponent)

    private val compositeDisposable = CompositeDisposable()

    fun setFavorites() {
        compositeDisposable.add(
            model.getFavoritesCards()
            .subscribeOn(Schedulers.io())
            .subscribe {
                var pokemonsList = listOf<BasePokemonModel>()
                if (it.isNotEmpty())
                    pokemonsList = BasePokemonModel.cast(it) { pokemonInfo ->
                        BasePokemonModel(
                            pokemonInfo.name,
                            pokemonInfo.images.small,
                            pokemonInfo.isFavorite
                        )
                    }
                pokemonsLiveData.postValue(pokemonsList)
            }
        )
    }
}