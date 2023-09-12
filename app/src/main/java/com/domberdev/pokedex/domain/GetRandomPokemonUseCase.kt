package com.domberdev.pokedex.domain

import com.domberdev.pokedex.data.PokemonRepository
import com.domberdev.pokedex.domain.model.Pokemon
import javax.inject.Inject

class GetRandomPokemonUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(): Pokemon? {
        val pkm = repository.getAllPokemonFromDatabase()
        if (!pkm.isNullOrEmpty()) {
            val randomNumber = (pkm.indices).random() // Retonar un numero aleatorio de un Pkm
            return pkm[randomNumber]
        }
        return null
    }
}