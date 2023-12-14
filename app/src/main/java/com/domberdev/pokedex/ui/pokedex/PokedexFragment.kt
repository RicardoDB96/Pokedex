package com.domberdev.pokedex.ui.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.domberdev.pokedex.databinding.FragmentPokedexBinding
import com.domberdev.pokedex.domain.model.Pokemon
import com.domberdev.pokedex.ui.pokedex.adapter.PokemonAdapter

class PokedexFragment : Fragment() {

    private var _binding: FragmentPokedexBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPokedexBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initRecyclerView()
    }

    private fun initRecyclerView(pokemonList: List<Pokemon>) {
        val rv = binding.rvPokedex
        rv.layoutManager = GridLayoutManager(requireContext(), 2)
        rv.adapter = PokemonAdapter(pokemonList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}