package com.domberdev.pokedex.ui.pokedex

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domberdev.pokedex.data.PokemonRepository
import com.domberdev.pokedex.domain.GetPokemonUseCase
import com.domberdev.pokedex.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val repository: PokemonRepository
) : ViewModel() {

    val pokedexList = MutableLiveData<List<Pokemon>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            if (!repository.getAllPokemonFromDatabase().isNullOrEmpty()) {
                pokedexList.postValue(repository.getAllPokemonFromDatabase())
                isLoading.postValue(false)
            } else {
                val result = getPokemonUseCase()

                if (!result.isNullOrEmpty()) {
                    pokedexList.postValue(result)
                    isLoading.postValue(false)
                }
            }
        }
    }
}