package com.example.mypokemons.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView

class BnvHandler(
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
