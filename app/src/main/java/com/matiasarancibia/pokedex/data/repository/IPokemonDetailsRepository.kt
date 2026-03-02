package com.matiasarancibia.pokedex.data.repository

import com.matiasarancibia.pokedex.core.common.BaseRepository
import com.matiasarancibia.pokedex.core.common.Result
import com.matiasarancibia.pokedex.core.database.entities.PokemonDetailsEntity
import com.matiasarancibia.pokedex.core.database.entities.PokemonSpeciesSectionEntity
import com.matiasarancibia.pokedex.data.model.PokemonDetailsData
import com.matiasarancibia.pokedex.data.model.PokemonSpeciesSectionData
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.domain.model.PokemonSpeciesSectionViewData

interface IPokemonDetailsRepository : BaseRepository {

    suspend fun getPokemonDetailsByUrl(pokemonUrl: String): Result<PokemonDetailsData>

    suspend fun getPokemonSpecies(pokemonSpeciesUrl: String): PokemonSpeciesSectionData

    suspend fun savePokemonSpeciesToDB(pokemonSpecies: PokemonSpeciesSectionViewData)

    suspend fun getStoredPokemonSpecies(pokemonNumber: Int): PokemonSpeciesSectionEntity?

    suspend fun savePokemonDetailsToDB(pokemonDetails: PokemonDetailsViewData)

    suspend fun getStoredPokemonDetails(pokemonNumber: Int): PokemonDetailsEntity?

    suspend fun saveFullPokemonInfoToDB(pokemonDetails: PokemonDetailsViewData, pokemonSpeciesSection: PokemonSpeciesSectionViewData)

    suspend fun getFullPokemonInfoFromDB(pokemonNumber: Int): PokemonDetailsViewData?

    suspend fun saveFavoritesPokemonToDB(pokemonDetails: PokemonDetailsViewData, pokemonSpeciesSection: PokemonSpeciesSectionViewData)

    suspend fun removeFavoritePokemonFromDB(pokemonNumber: Int)

    suspend fun getAllFavoritesPokemonDB(): List<PokemonDetailsViewData>
}