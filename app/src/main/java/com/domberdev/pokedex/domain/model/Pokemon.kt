package com.domberdev.pokedex.domain.model

import com.domberdev.pokedex.data.database.entities.PokemonEntity
import com.domberdev.pokedex.data.model.PokemonModel

data class Pokemon(
    val name: String,
    val url: String
) {
    val id: Int get() {
        val components = url.split("/")
        return components[components.size - 2].toIntOrNull() ?: 0
    }

    val imageUrl: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}

fun PokemonModel.toDomain() = Pokemon(name, url)
fun PokemonEntity.toDomain() = Pokemon(name, url)
