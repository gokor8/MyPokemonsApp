package com.example.mypokemons.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.mypokemons.R
import com.example.mypokemons.databinding.ActivityMainBinding
import com.example.mypokemons.ui.fragments.PokemonCardsFragment
import com.example.mypokemons.ui.fragments.PokemonFavoritesFragment
import com.example.mypokemons.ui.fragments.PokemonSearchFragment
import com.example.mypokemons.viewModels.PokemonCardsViewModel
import com.example.mypokemons.viewModels.PokemonFavoritesViewModel
import com.example.mypokemons.viewModels.SearchPokemonCardsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.searchSrcText.clearFocus()

        val bnvHandler = BnvHandler(
            binding.fragmentContainerView, mapOf(
                R.id.pokemon_cards to PokemonCardsFragment(),
                R.id.pokemon_favorites to PokemonFavoritesFragment()
            )
        )

        binding.bnvMain.setOnItemSelectedListener { menuItem ->
            bnvHandler.changeFragment(menuItem.itemId) {
                supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainerView, it)
                    .commit()
            }
            true
        }

        binding.toolbar.ivBack.setOnClickListener {
            binding.bnvMain.selectedItemId = R.id.pokemon_cards
            supportFragmentManager.commit {
                addToBackStack(null)
                replace(
                    R.id.fragmentContainerView,
                    PokemonCardsFragment()
                )
            }
        }

        binding.toolbar.run {
            searchButton.setOnClickListener {
                supportFragmentManager.commit {
                    addToBackStack(null)
                    replace(
                        R.id.fragmentContainerView,
                        PokemonSearchFragment.newInstance(searchSrcText.text.toString())
                    )
                }
            }
        }
    }
}