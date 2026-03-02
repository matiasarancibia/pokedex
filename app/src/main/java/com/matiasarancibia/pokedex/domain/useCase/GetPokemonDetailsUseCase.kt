package com.matiasarancibia.pokedex.domain.useCase

import com.matiasarancibia.pokedex.R
import com.matiasarancibia.pokedex.core.common.Result
import com.matiasarancibia.pokedex.data.model.PokemonSpeciesSectionData
import com.matiasarancibia.pokedex.data.repository.PokemonDetailsRepositoryImpl
import com.matiasarancibia.pokedex.ui.util.AppResourcesManager
import com.matiasarancibia.pokedex.ui.util.extensions.letNotEmpty
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetPokemonDetailsUseCase @Inject constructor(
    private val repository: PokemonDetailsRepositoryImpl,
    private val appResourcesManager: AppResourcesManager
) {

    suspend fun fetchPokedexSpeciesInfo(
        pokemonSpeciesUrl: String?
    ): Result<PokemonSpeciesSectionData> = coroutineScope {

        try {
            pokemonSpeciesUrl?.letNotEmpty {
                // We get the pokemon species data to obtain more detailed information about the specific pokemon
                val pokemonSpecies = async { repository.getPokemonSpecies(pokemonSpeciesUrl) }
                val pokemonSpeciesSection = pokemonSpecies.await()

                Result.success(pokemonSpeciesSection)
            } ?: run {
                Result.error(Exception(appResourcesManager.getString(R.string.error_empty_pokemon_species_url)))
            }
        } catch (e: Exception) {
            /*
                If there was an error in any API call then we return the error result
                with the exception to be handled in the view model
             */
            Result.error(e)
        }
    }
}