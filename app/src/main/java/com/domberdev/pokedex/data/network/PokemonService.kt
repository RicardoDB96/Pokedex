package com.domberdev.pokedex.data.network

import com.domberdev.pokedex.data.model.PokemonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonService @Inject constructor(private val api: PokemonApiClient) {

    suspend fun getAllPokemon(): List<PokemonModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllPokemon()
            response.body()?.pokemonList ?: listOf()
        }
    }
}