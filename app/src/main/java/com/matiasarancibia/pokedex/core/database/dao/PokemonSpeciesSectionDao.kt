package com.matiasarancibia.pokedex.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matiasarancibia.pokedex.core.database.entities.PokemonSpeciesSectionEntity

@Dao
interface PokemonSpeciesSectionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokemonSpeciesSection(pokemonSpeciesSection: PokemonSpeciesSectionEntity)

    @Query("SELECT * FROM pokemonSpeciesSection WHERE id = :pokemonNumber")
    suspend fun getPokemonSpeciesSection(pokemonNumber: Int): PokemonSpeciesSectionEntity?
}