package com.domberdev.pokedex.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.domberdev.pokedex.domain.model.Pokemon

@Entity( "pokemon_table")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int = 0,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("url") val url: String
)

fun Pokemon.toDatabase() = PokemonEntity(name = name, url = url)