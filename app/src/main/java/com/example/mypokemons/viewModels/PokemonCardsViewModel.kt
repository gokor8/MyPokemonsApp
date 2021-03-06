package com.example.mypokemons.viewModels

import android.app.Application
import android.util.Log
import com.example.domain.models.PokemonsCardsModel
import io.reactivex.schedulers.Schedulers

class PokemonCardsViewModel(application: Application) : BaseViewModel(application) {
    private var model = PokemonsCardsModel(appComponent)

    private var wasDataFromInternet = false

    override fun updateData() {
        if (!wasDataFromInternet) {
            updateDataFromInternet()
            wasDataFromInternet = true
        }
        else
            updateDataFromDatabase()
    }

    private fun updateDataFromDatabase(){
        val disposeSub = model.getDbCards()
            .subscribeOn(Schedulers.io())
            .onErrorResumeNext {
                Log.e("CardsError", it.message.toString())
                return@onErrorResumeNext model.getDbCards()
            }
            .subscribe({
                //if(pokemonsLiveData.value != it)
                pokemonsLiveData.postValue(it)
            }, {
                Log.d("RoomError", it.stackTraceToString())
            }
            )
        compositeDisposable.add(disposeSub)
    }

    private fun updateDataFromInternet() {
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
                //if(pokemonsLiveData.value != it)
                pokemonsLiveData.postValue(it)
            }, {
                Log.d("RoomError", it.stackTraceToString())
            }
            )
        compositeDisposable.add(disposeSub)
    }
}
