package com.domberdev.pokedex.data.model

// Serialized nos sirve para cuando obtenemos datos de una API y estos no tienen exactamente el
// mismo nombre que el de nuestra variable, con este podemos declarar ese nombre de la API
import com.google.gson.annotations.SerializedName

data class PokemonListModel(@SerializedName("results") val pokemonList: List<PokemonModel>)

data class PokemonModel(@SerializedName("name") val name: String, @SerializedName("url") val url: String)
