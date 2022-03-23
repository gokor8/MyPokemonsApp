package com.example.mypokemons.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.models.BasePokemonModel
import com.example.mypokemons.ui.BaseApplication
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val pokemonsLiveData = MutableLiveData<List<BasePokemonModel>>()

    protected val appComponent = (application as BaseApplication).getAppComponent()
    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }

    abstract fun updateData()
}