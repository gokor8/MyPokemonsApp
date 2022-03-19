package com.example.mypokemons.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mypokemons.R
import com.example.mypokemons.databinding.ActivityMainBinding
import com.example.mypokemons.view_models.MainViewModel
import com.example.mypokemons.view_models.PokemonCardsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                as NavHostFragment

        binding.bnvBase.setupWithNavController(navHostFragment.navController)

        binding.toolbar.ivBack.setOnClickListener {

        }

        binding.toolbar.searchButton.setOnClickListener {

        }
    }
}