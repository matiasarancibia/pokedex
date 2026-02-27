package com.matiasarancibia.pokedex.data.repository

import com.matiasarancibia.pokedex.core.common.BaseRepository
import com.matiasarancibia.pokedex.core.common.Result
import com.matiasarancibia.pokedex.data.model.PokedexSectionData
import com.matiasarancibia.pokedex.data.model.PokemonDetailsData
import com.matiasarancibia.pokedex.data.model.PokemonSpeciesSectionData

interface IPokemonDetailsRepository : BaseRepository {

    suspend fun getPokemonDetailsByUrl(pokemonUrl: String): Result<PokemonDetailsData>

    suspend fun getPokedexEntry(pokedexNumber: Int): PokedexSectionData

    suspend fun getPokemonSpecies(pokemonSpeciesUrl: String): PokemonSpeciesSectionData
}