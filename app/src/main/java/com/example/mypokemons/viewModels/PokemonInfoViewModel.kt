package com.example.mypokemons.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.PokemonInfoModel
import com.example.domain.models.models.FullPokemonModel
import com.example.mypokemons.ui.BaseApplication
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PokemonInfoViewModel(application: Application) :
    AndroidViewModel(application) {

    val infoLiveData = MutableLiveData<FullPokemonModel>()

    private val appComponent = (application as BaseApplication).getAppComponent()
    private var model = PokemonInfoModel(appComponent)

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun updateFavorite(id: String, isFavorite: Boolean) {
        compositeDisposable.add(
            model.getCardById(id)
            .subscribeOn(Schedulers.io())
                .map {
                    it.isFavorite = !it.isFavorite
                    model.updateFavorite(it)
                    it
                }
                .flatMap {
                    model.getCardById(it.pId)
                }
            .subscribe { pokemonDb ->
                infoLiveData.value?.run {
                    val fillModel = FullPokemonModel(
                        id, name, image, pokemonDb.isFavorite, rare, type, subtype, health, typeAttack
                    )
                    infoLiveData.postValue(fillModel)
                }
            })
    }

    fun setPreviewData(id: String) {
        val disposeSub = model.getCardById(id)
            .subscribeOn(Schedulers.io())
            .subscribe { dbPokemons ->
                    dbPokemons.apply {
                        val fillModel = FullPokemonModel(
                            pId,
                            name,
                            images.large,
                            isFavorite,
                            rare,
                            type,
                            subtype,
                            health,
                            typeAttack
                        )
                        infoLiveData.postValue(fillModel)
                    }
            }

        compositeDisposable.add(disposeSub)
    }
}