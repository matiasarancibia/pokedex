package com.matiasarancibia.pokedex.domain.useCase

import com.matiasarancibia.pokedex.core.common.Result
import com.matiasarancibia.pokedex.data.model.PokemonSpeciesSectionData
import com.matiasarancibia.pokedex.data.repository.PokemonDetailsRepositoryImpl
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetPokemonDetailsUseCase @Inject constructor(
    private val repository: PokemonDetailsRepositoryImpl
) {

    suspend fun getPokedexEntry(
        pokedexNumber: Int,
        pokemonName: String
    ): Result<PokemonSpeciesSectionData> = coroutineScope {
        /*
            He we need to call this endpoint to get the list of entries on the specific pokedex (National, Generation 1, etc),
            which contains a url that we need to use to get the pokedex entry itself for the specific pokemon.
         */
        try {
            // Performing the first API call to get the pokedex entry
            val pokedexEntry = async { repository.getPokedexEntry(pokedexNumber) }
            val pokedexSection = pokedexEntry.await()

            val pokemonSpeciesUrl = pokedexSection.pokemonEntries?.firstOrNull {
                it.pokemonSpecies.name?.equals(pokemonName, true) == true
            }?.pokemonSpecies?.url

            // Performing the second API call to get the pokedex entry itself by using the url obtained in the previous call
            val pokemonSpecies = async { repository.getPokemonSpecies(pokemonSpeciesUrl.orEmpty()) }
            val pokemonSpeciesSection = pokemonSpecies.await()

            Result.success(pokemonSpeciesSection)
        } catch (e: Exception) {
            /*
                If there was an error in any API call then we return the error result
                with the exception to be handled in the view model
             */
            Result.error(e)
        }
    }
}