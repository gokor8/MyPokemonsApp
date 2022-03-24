package com.example.mypokemons.viewModels

import android.app.Application
import android.util.Log
import com.example.domain.models.PokemonsCardsModel
import io.reactivex.schedulers.Schedulers

class PokemonCardsViewModel(application: Application) : BaseViewModel(application) {
    private var model = PokemonsCardsModel(appComponent)

    override fun updateData() {
        val disposeSub = model.getWebCards()
            .subscribeOn(Schedulers.io())
            .flatMap {
                model.syncDbCards(it.pokemons)
            }
            .map {
                model.castDbCards(it)
            }
            .onErrorResumeNext {
                Log.e("CardsError", it.message.toString())
                return@onErrorResumeNext model.getDbCards()
            }
            .subscribe({
                if(pokemonsLiveData.value?.size != it.size)
                    pokemonsLiveData.postValue(it)
            }, {
                Log.d("RoomError", it.stackTraceToString())
            }
            )
        compositeDisposable.add(disposeSub)
    }
}
