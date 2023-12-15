package com.domberdev.pokedex.ui.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.domberdev.pokedex.R
import com.domberdev.pokedex.databinding.FragmentRandomPokemonBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomPokemonFragment : Fragment() {

    private val pokemonViewModel: PokemonViewModel by viewModels<PokemonViewModel>()

    private var _binding: FragmentRandomPokemonBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonViewModel.onCreate()

        // Ponemos la información de los Pokemon en sus TextViews
        pokemonViewModel.pokemonModel.observe(viewLifecycleOwner) { pkm ->
            binding.tvName.text = pkm.name.replaceFirstChar { it.uppercase() }
            binding.tvNumber.text = getString(R.string.pokemon_number, pkm.id)
            Picasso.get().load(pkm.imageUrl).into(binding.ivPokemon)
        }
        pokemonViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progress.isVisible = it
        }

        // Cada click en el container retornara un nuevo PokemonModel
        binding.root.setOnClickListener {
            pokemonViewModel.randomPokemon()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRandomPokemonBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}