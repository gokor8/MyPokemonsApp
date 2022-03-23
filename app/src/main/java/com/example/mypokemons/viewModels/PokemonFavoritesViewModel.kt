package com.example.mypokemons.viewModels

import android.app.Application
import com.example.domain.models.PokemonFavoritesModel
import com.example.domain.models.model.BasePokemonModel
import io.reactivex.schedulers.Schedulers

class PokemonFavoritesViewModel(application: Application) : BaseViewModel(application) {
    private var model = PokemonFavoritesModel(appComponent)

    override fun updateData() {
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