package com.domberdev.pokedex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.domberdev.pokedex.data.database.dao.PokemonDao
import com.domberdev.pokedex.data.database.entities.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDatabase: RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao
}