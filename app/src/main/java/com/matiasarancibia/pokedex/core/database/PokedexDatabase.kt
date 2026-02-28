package com.matiasarancibia.pokedex.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.matiasarancibia.pokedex.core.database.dao.FavoritesDao
import com.matiasarancibia.pokedex.core.database.dao.PokemonDetailsDao
import com.matiasarancibia.pokedex.core.database.dao.PokemonSpeciesSectionDao
import com.matiasarancibia.pokedex.core.database.entities.PokemonDetailsEntity
import com.matiasarancibia.pokedex.core.database.entities.PokemonDetailsFullEntity
import com.matiasarancibia.pokedex.core.database.entities.PokemonSpeciesSectionEntity

@Database(
    entities = [
        PokemonDetailsEntity::class,
        PokemonSpeciesSectionEntity::class,
        PokemonDetailsFullEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    PokemonTypesConverters::class
)
abstract class PokedexDatabase : RoomDatabase() {
    abstract val favoritesDao: FavoritesDao
    abstract val pokemonDetailsDao: PokemonDetailsDao
    abstract val pokemonSpeciesSectionDao: PokemonSpeciesSectionDao
}