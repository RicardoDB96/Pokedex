package com.domberdev.pokedex.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.domberdev.pokedex.databinding.ActivityMainBinding
import com.domberdev.pokedex.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val pokemonViewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pokemonViewModel.onCreate()

        // Ponemos la información de los Pokemon en sus TextViews
        pokemonViewModel.pokemonModel.observe(this) { pkm ->
            binding.tvName.text = pkm.name.replaceFirstChar { it.uppercase() }
            binding.tvNumber.text = pkm.id.toString()
        }
        pokemonViewModel.isLoading.observe(this) {
            binding.progress.isVisible = it
        }

        // Cada click en el container retornara un nuevo PokemonModel
        binding.viewContainer.setOnClickListener {
            pokemonViewModel.randomPokemon()
        }
    }
}