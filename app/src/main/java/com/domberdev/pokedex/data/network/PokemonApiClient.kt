package com.domberdev.pokedex.data.network

import com.domberdev.pokedex.data.model.PokemonListModel
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApiClient {
    @GET("pokemon?limit=151")
    suspend fun getAllPokemon(): Response<PokemonListModel>
}