package com.domberdev.pokedex.ui.pokedex.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.domberdev.pokedex.R
import com.domberdev.pokedex.domain.model.Pokemon

class PokemonViewHolder(view: View) : ViewHolder(view) {

    val pokemonImage = view.findViewById<ImageView>(R.id.ivPokemon)
    val pokemonName = view.findViewById<TextView>(R.id.tvPokemonName)
    val pokemonNumber = view.findViewById<TextView>(R.id.tvPokemonNumber)

    fun render(pokemonModel: Pokemon) {
        pokemonName.text = pokemonModel.name
        pokemonNumber.text = itemView.context.getString(R.string.pokemon_number, pokemonModel.id)
    }
}