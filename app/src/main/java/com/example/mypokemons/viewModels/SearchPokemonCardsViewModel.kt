package com.example.mypokemons.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.SearchPokemonsModel
import com.example.mypokemons.data.storage.PokemonModel
import com.example.mypokemons.ui.BaseApplication
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchPokemonCardsViewModel(application: Application) : AndroidViewModel(application) {
    val foundPokemonsLiveData = MutableLiveData<PokemonModel>()

    private val compositeDisposable = CompositeDisposable()

    val appComponent = (application as BaseApplication).getAppComponent()

    private val model = SearchPokemonsModel(appComponent.getApiService())

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun search(name: String) {
        /*compositeDisposable.add(
            model.getFoundCards(name)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.d("Req", it.pokemons.size.toString())
                    foundPokemonsLiveData.postValue(it)
                }, {
                    Log.d("ReqError", it.message.toString())
                        foundPokemonsLiveData.notify()
                })
        )*/
    }
}