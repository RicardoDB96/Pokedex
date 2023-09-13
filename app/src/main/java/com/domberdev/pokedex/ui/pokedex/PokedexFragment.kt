package com.domberdev.pokedex.ui.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.domberdev.pokedex.R
import com.domberdev.pokedex.databinding.FragmentPokedexBinding

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
}