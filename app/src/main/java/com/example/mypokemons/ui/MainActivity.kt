package com.example.mypokemons.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.mypokemons.PocemonFavorites
import com.example.mypokemons.R
import com.example.mypokemons.databinding.ActivityMainBinding
import com.example.mypokemons.ui.fragments.PokemonCardsFragment
import com.example.mypokemons.ui.fragments.SearchPokemonCardsFragment
import com.example.mypokemons.view_models.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, PokemonCardsFragment())
            .setReorderingAllowed(true)
            .commit()

        val bnvhandler = BottomNavigationHandler(
            binding.fragmentContainerView, hashMapOf(
                R.id.pokemon_cards to PokemonCardsFragment(),
                R.id.pokemon_favorites to PocemonFavorites()
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
                replace(R.id.fragmentContainerView, PokemonCardsFragment())
                setReorderingAllowed(true)
            }
        }

        binding.toolbar.searchButton.setOnClickListener {
            supportFragmentManager.commit {
                replace(
                    R.id.fragmentContainerView, SearchPokemonCardsFragment(
                        binding.toolbar.searchSrcText.text.toString()
                    )
                )
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }
}