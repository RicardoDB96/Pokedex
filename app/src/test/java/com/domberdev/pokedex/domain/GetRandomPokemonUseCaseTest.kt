package com.domberdev.pokedex.domain

import com.domberdev.pokedex.data.PokemonRepository
import com.domberdev.pokedex.domain.model.Pokemon
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetRandomPokemonUseCaseTest {

    @RelaxedMockK
    private lateinit var pokemonRepository: PokemonRepository

    lateinit var getRandomPokemonUseCase: GetRandomPokemonUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRandomPokemonUseCase = GetRandomPokemonUseCase(pokemonRepository)
    }

    @Test
    fun `when database is empty then return null`() = runBlocking {
        // Given
        coEvery { pokemonRepository.getAllPokemonFromDatabase() } returns emptyList()

        // When
        val response = getRandomPokemonUseCase()

        // Then
        assert(response == null)
    }

    @Test
    fun `when database is not empty then return pokemon`() = runBlocking {
        // Given
        val pokemonList = listOf(Pokemon("Rayquaza", "https://pokeapi.co/api/v2/pokemon/384/"))
        coEvery { pokemonRepository.getAllPokemonFromDatabase() } returns pokemonList

        // When
        val response = getRandomPokemonUseCase()

        // Then
        assert(response == pokemonList.first())
    }
}