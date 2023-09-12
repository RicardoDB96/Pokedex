package com.domberdev.pokedex.domain

import com.domberdev.pokedex.data.PokemonRepository
import com.domberdev.pokedex.data.database.entities.toDatabase
import com.domberdev.pokedex.domain.model.Pokemon
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(): List<Pokemon> {
        val pokemon = repository.getAllPokemonFromApi()

        return if (pokemon.isNotEmpty()) {
            repository.clearPokemon()
            repository.insertPokemon(pokemon.map { it.toDatabase() })
            pokemon
        } else {
            repository.getAllPokemonFromDatabase()
        }
    }
}