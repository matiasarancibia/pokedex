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

    suspend fun fetchPokedexSpeciesInfo(
        pokedexIdNumber: Int,
        pokemonNumber: Int
    ): Result<PokemonSpeciesSectionData> = coroutineScope {
        /*
            He we need to call this endpoint to get the list of entries on the specific pokedex (National, Generation 1, etc),
            which contains a url that we need to use to get the pokedex entry itself for the specific pokemon.
         */
        try {
            // Performing the first API call to get the pokedex entry
            val pokedexEntry = async { repository.getPokedexEntry(pokedexIdNumber) }
            val pokedexSection = pokedexEntry.await()

            val pokemonSpeciesUrl = pokedexSection.pokemonEntries?.firstOrNull {
                /*
                    We need to get the pokemon number from the url, so we will take
                    the number before the last dash in the url to identify the base pokemon that we need for this request.
                 */
                val pokemonUrlArray = it.pokemonSpecies.url?.split("/").orEmpty()

                val cleanPokemonNumber = pokemonUrlArray.lastOrNull { lastElement -> lastElement.isNotEmpty() }

                cleanPokemonNumber == pokemonNumber.toString()
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