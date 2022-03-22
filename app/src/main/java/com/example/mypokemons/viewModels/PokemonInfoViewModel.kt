package com.example.mypokemons.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.PokemonInfoModel
import com.example.domain.models.model.BasePokemonModel
import com.example.domain.models.model.FullPokemonModel
import com.example.mypokemons.ui.BaseApplication
import io.reactivex.android.schedulers.AndroidSchedulers
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

    fun updateFavorite(name: String, isFavorite: Boolean) {
        compositeDisposable.add(
            model.getCardByName(name)
            .subscribeOn(Schedulers.io())
                .flatMap {
                    it.isFavorite = !isFavorite
                    model.updateFavorite(it)
                }
            .subscribe { pokemonDb ->
                infoLiveData.value?.run {
                    val fillModel = FullPokemonModel(
                        name, image, pokemonDb.isFavorite, rare, type, subtype, health, typeAttack
                    )
                    infoLiveData.postValue(fillModel)
                }
            })
    }

    fun setPreviewData(name: String) {
        val disposeSub = model.getCardInfo(name)
            .subscribeOn(Schedulers.io())
            .subscribe { dbPokemons ->
                if (dbPokemons.isNotEmpty())
                    dbPokemons[0].run {
                        val fillModel = FullPokemonModel(
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