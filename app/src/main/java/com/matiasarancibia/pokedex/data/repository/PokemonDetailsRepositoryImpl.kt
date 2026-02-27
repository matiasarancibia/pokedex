package com.matiasarancibia.pokedex.data.repository

import com.matiasarancibia.pokedex.core.common.Result
import com.matiasarancibia.pokedex.data.model.PokedexSectionData
import com.matiasarancibia.pokedex.data.model.PokemonDetailsData
import com.matiasarancibia.pokedex.data.model.PokemonSpeciesSectionData
import com.matiasarancibia.pokedex.domain.repository.PokedexApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonDetailsRepositoryImpl @Inject constructor(
    private val pokedexApiService: PokedexApiService
) : IPokemonDetailsRepository {

    override suspend fun getPokemonDetailsByUrl(
        pokemonUrl: String
    ): Result<PokemonDetailsData> {
        return request {
            pokedexApiService.getPokemonDetailsByUrl(pokemonUrl)
        }
    }

    override suspend fun getPokedexEntry(
        pokedexNumber: Int
    ): PokedexSectionData {
        return withContext(Dispatchers.IO) {
//            request {
                pokedexApiService.getPokedexEntry(pokedexNumber)
//            }
        }
    }

    override suspend fun getPokemonSpecies(
        pokemonSpeciesUrl: String
    ): PokemonSpeciesSectionData {
        return withContext(Dispatchers.IO) {
//            request {
                pokedexApiService.getPokemonSpecies(pokemonSpeciesUrl)
//            }
        }
    }
}