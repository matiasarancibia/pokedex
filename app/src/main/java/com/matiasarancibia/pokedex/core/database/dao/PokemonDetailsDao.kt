package com.matiasarancibia.pokedex.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matiasarancibia.pokedex.core.database.entities.PokemonDetailsEntity
import com.matiasarancibia.pokedex.core.database.entities.PokemonDetailsFullEntity
import com.matiasarancibia.pokedex.core.database.entities.PokemonSpeciesSectionEntity

@Dao
interface PokemonDetailsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokemonDetails(pokemonDetails: PokemonDetailsEntity)

    @Query("SELECT * FROM pokemonDetails WHERE number = :pokemonNumber")
    suspend fun getPokemonDetails(pokemonNumber: Int): PokemonDetailsEntity?

    @Query("SELECT * FROM pokemonSpeciesSection")
    suspend fun getPokemonSpeciesSection(): PokemonSpeciesSectionEntity

    @Query("SELECT * FROM pokemonDetails, pokemonSpeciesSection WHERE pokemonDetails.number = pokemonSpeciesSection.id")
    suspend fun getPokemonInfo(): PokemonDetailsFullEntity
}