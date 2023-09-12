package com.domberdev.pokedex.data

import com.domberdev.pokedex.data.database.dao.PokemonDao
import com.domberdev.pokedex.data.database.entities.PokemonEntity
import com.domberdev.pokedex.data.network.PokemonService
import com.domberdev.pokedex.domain.model.Pokemon
import com.domberdev.pokedex.domain.model.toDomain
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonService,
    private val pokemonDao: PokemonDao
) {

    suspend fun getAllPokemonFromApi(): List<Pokemon> {
        val response = api.getAllPokemon()
        return response.map { it.toDomain() }
    }

    suspend fun getAllPokemonFromDatabase(): List<Pokemon> {
        val response = pokemonDao.getAllPokemon()
        return response.map { it.toDomain() }
    }

    suspend fun insertPokemon(pokemon: List<PokemonEntity>) {
        pokemonDao.insertAll(pokemon)
    }

    suspend fun clearPokemon() {
        pokemonDao.deleteAllPokemon()
    }
}