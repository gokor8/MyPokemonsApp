package com.example.mypokemons.viewModels

import android.app.Application
import com.example.domain.models.PokemonFavoritesModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PokemonFavoritesViewModel(application: Application) : BaseViewModel(application) {
    private var model = PokemonFavoritesModel(appComponent)

    override fun updateData() {
        compositeDisposable.add(
            model.getFavoritesCards()
                .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                val castedPokemons = model.castDbCards(it)

                pokemonsLiveData.postValue(castedPokemons)
            }
        )
    }
}