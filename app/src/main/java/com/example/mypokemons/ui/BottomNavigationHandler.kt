package com.example.mypokemons.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.mypokemons.R
import com.example.mypokemons.ui.fragments.PokemonCardsFragment

class BottomNavigationHandler(
    private val fcv: FragmentContainerView,
    val fragments: HashMap<Int, Fragment> = hashMapOf<Int, Fragment>()
) {

    fun changeFragment(id: Int, change: (Fragment) -> Unit) {
        fragments.forEach { (fId, fragment) ->
            if (id == fId) {
                change(fragment)
            }
        }
    }
}
