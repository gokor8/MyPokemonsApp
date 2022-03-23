package com.example.mypokemons.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.mypokemons.R
import com.example.mypokemons.databinding.ActivityMainBinding
import com.example.mypokemons.ui.fragments.PokemonCardsFragment
import com.example.mypokemons.viewModels.PokemonCardsViewModel
import com.example.mypokemons.viewModels.PokemonFavoritesViewModel
import com.example.mypokemons.viewModels.SearchPokemonCardsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainerView,
                PokemonCardsFragment(PokemonCardsViewModel(application))
            )
            .setReorderingAllowed(true)
            .commit()

        val bnvhandler = BottomNavigationHandler(
            binding.fragmentContainerView, hashMapOf(
                R.id.pokemon_cards to PokemonCardsFragment(PokemonCardsViewModel(application)),
                R.id.pokemon_favorites to PokemonCardsFragment(PokemonFavoritesViewModel(application))
            )
        )

        binding.bnvMain.setOnItemSelectedListener {
            bnvhandler.changeFragment(it.itemId) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, it)
                    .setReorderingAllowed(true)
                    .commit()
            }
            true
        }

        binding.toolbar.ivBack.setOnClickListener {
            binding.bnvMain.selectedItemId = R.id.pokemon_cards
            supportFragmentManager.commit {
                replace(
                    R.id.fragmentContainerView,
                    PokemonCardsFragment(PokemonCardsViewModel(application))
                )
                setReorderingAllowed(true)
            }
        }

        binding.toolbar.run {
            searchButton.setOnClickListener {
                supportFragmentManager.commit {
                    replace(
                        R.id.fragmentContainerView,
                        PokemonCardsFragment(
                            SearchPokemonCardsViewModel(
                                application,
                                searchSrcText.text.toString()
                            )
                        )
                    )
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }
        }
    }
}