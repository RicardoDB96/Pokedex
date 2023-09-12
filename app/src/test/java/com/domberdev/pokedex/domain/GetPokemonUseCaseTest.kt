package com.domberdev.pokedex.domain

import com.domberdev.pokedex.data.PokemonRepository
import com.domberdev.pokedex.domain.model.Pokemon
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPokemonUseCaseTest {

    @RelaxedMockK
    private lateinit var pokemonRepository: PokemonRepository

    lateinit var getPokemonUseCase: GetPokemonUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPokemonUseCase = GetPokemonUseCase(pokemonRepository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        // Given
        coEvery { pokemonRepository.getAllPokemonFromApi() } returns emptyList()

        // When
        getPokemonUseCase()

        // Then
        coVerify(exactly = 1) { pokemonRepository.getAllPokemonFromDatabase() }
    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        // Given
        val myList = listOf(Pokemon("Rayquaza", "https://pokeapi.co/api/v2/pokemon/384/"))
        coEvery { pokemonRepository.getAllPokemonFromApi() } returns myList

        // When
        val response = getPokemonUseCase()

        // Then
        coVerify(exactly = 1) { pokemonRepository.clearPokemon() }
        coVerify(exactly = 1) { pokemonRepository.insertPokemon(any()) }
        coVerify(exactly = 0) { pokemonRepository.getAllPokemonFromDatabase() }
        assert(myList == response)
    }
}