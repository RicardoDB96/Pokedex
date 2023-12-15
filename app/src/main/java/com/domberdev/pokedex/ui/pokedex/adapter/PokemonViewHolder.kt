package com.domberdev.pokedex.ui.pokedex.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.domberdev.pokedex.R
import com.domberdev.pokedex.databinding.ItemPokemonBinding
import com.domberdev.pokedex.domain.model.Pokemon
import com.squareup.picasso.Picasso

class PokemonViewHolder(view: View) : ViewHolder(view) {

    private val binding = ItemPokemonBinding.bind(view)

    fun render(pokemonModel: Pokemon) {
        binding.tvPokemonName.text = pokemonModel.name.replaceFirstChar { it.uppercase() }
        binding.tvPokemonNumber.text = itemView.context.getString(R.string.pokemon_number, pokemonModel.id)
        Picasso.get().load(pokemonModel.imageUrl).into(binding.ivPokemon)
    }
}