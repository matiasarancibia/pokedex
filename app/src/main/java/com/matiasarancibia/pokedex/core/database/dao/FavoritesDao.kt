package com.matiasarancibia.pokedex.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matiasarancibia.pokedex.core.database.entities.PokemonDetailsFullEntity

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoritePokemon(pokemonDetails: PokemonDetailsFullEntity)

    @Query("DELETE FROM pokemonDetailsFull WHERE number = :pokemonNumber")
    suspend fun deleteFavoritePokemon(pokemonNumber: Int)

    @Query("SELECT * FROM pokemonDetailsFull")
    suspend fun getAllFavorites(): List<PokemonDetailsFullEntity>
}