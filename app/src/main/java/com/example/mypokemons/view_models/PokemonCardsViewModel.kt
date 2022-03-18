package com.example.mypokemons.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.PokemonsModel
import com.example.mypokemons.data.storage.PokemonsDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PokemonCardsViewModel : ViewModel() {
    val pokemonsLiveData = MutableLiveData<PokemonsDataModel>()

    private var model = PokemonsModel()

    fun getAllPokemons() {
        model.getAllCards()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { onNext ->
                    if(onNext.data.size != pokemonsLiveData.value?.data?.size)
                    pokemonsLiveData.postValue(onNext)
                },
                { onError ->
                    Log.d("OnErrorRx", "Error")
                }
            )
    }
}