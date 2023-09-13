package com.domberdev.pokedex.ui.random

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domberdev.pokedex.data.PokemonRepository
import com.domberdev.pokedex.domain.GetPokemonUseCase
import com.domberdev.pokedex.domain.GetRandomPokemonUseCase
import com.domberdev.pokedex.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val getRandomPokemonUseCase: GetRandomPokemonUseCase,
    private val repository: PokemonRepository
) : ViewModel() {

    val pokemonModel = MutableLiveData<Pokemon>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            // Muestra un progressbar en lo que recuperamos la info de la database o del server
            isLoading.postValue(true)
            if (!repository.getAllPokemonFromDatabase().isNullOrEmpty()) {
                randomPokemon()
            } else {
                val result = getPokemonUseCase()

                if (!result.isNullOrEmpty()) {
                    pokemonModel.postValue(result[result.indices.random()])
                    isLoading.postValue(false)
                }
            }
        }
    }

    fun randomPokemon() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val pkm = getRandomPokemonUseCase()
            if (pkm != null) {
                pokemonModel.postValue(pkm!!)
            }
            isLoading.postValue(false)
        }
    }
}