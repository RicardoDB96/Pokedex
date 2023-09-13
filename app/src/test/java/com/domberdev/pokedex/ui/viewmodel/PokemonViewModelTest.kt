package com.domberdev.pokedex.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.domberdev.pokedex.domain.GetPokemonUseCase
import com.domberdev.pokedex.domain.GetRandomPokemonUseCase
import com.domberdev.pokedex.domain.model.Pokemon
import com.domberdev.pokedex.ui.random.PokemonViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonViewModelTest {

    @RelaxedMockK
    private lateinit var getPokemonUseCase: GetPokemonUseCase

    @RelaxedMockK
    private lateinit var getRandomPokemonUseCase: GetRandomPokemonUseCase

    private lateinit var pokemonViewModel: PokemonViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        pokemonViewModel = PokemonViewModel(getPokemonUseCase, getRandomPokemonUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all quotes and set the first value`() =
        runTest {
            // Given
            val pokemonList = listOf(
                Pokemon("Rayquaza", "https://pokeapi.co/api/v2/pokemon/384/"),
                Pokemon("Bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/")
            )
            coEvery { getPokemonUseCase() } returns pokemonList

            // When
            pokemonViewModel.onCreate()

            // Then
            assert(pokemonViewModel.pokemonModel.value == pokemonList.first())
        }

    @Test
    fun `when randomPokemonUseCase return a pokemon set on the livedata`() = runTest {
        // Given
        val pokemon = Pokemon("Rayquaza", "https://pokeapi.co/api/v2/pokemon/384/")
        coEvery { getRandomPokemonUseCase() } returns pokemon

        // When
        pokemonViewModel.randomPokemon()

        // Then
        assert(pokemonViewModel.pokemonModel.value == pokemon)
    }
    
    @Test
    fun `if randomPokemonUseCase return null keep the last value`() = runTest {
        // Given
        val pokemon = Pokemon("Rayquaza", "https://pokeapi.co/api/v2/pokemon/384/")
        pokemonViewModel.pokemonModel.value = pokemon
        coEvery { getRandomPokemonUseCase() } returns null

        // When
        pokemonViewModel.randomPokemon()
        
        // Then
        assert(pokemonViewModel.pokemonModel.value == pokemon)
    }
}