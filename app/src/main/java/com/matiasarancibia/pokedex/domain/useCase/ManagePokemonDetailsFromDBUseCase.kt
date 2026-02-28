package com.matiasarancibia.pokedex.domain.useCase

import com.matiasarancibia.pokedex.core.common.Result
import com.matiasarancibia.pokedex.data.repository.PokemonDetailsRepositoryImpl
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.domain.model.PokemonSpeciesSectionViewData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ManagePokemonDetailsFromDBUseCase @Inject constructor(
    private val repository: PokemonDetailsRepositoryImpl
) {

    suspend fun savePokemonInfoToDB(
        pokemonDetails: PokemonDetailsViewData,
        pokemonSpeciesSection: PokemonSpeciesSectionViewData
    ) = withContext(Dispatchers.IO) {
        repository.saveFullPokemonInfoToDB(
            pokemonDetails = pokemonDetails,
            pokemonSpeciesSection = pokemonSpeciesSection
        )
    }

    suspend fun savePokemonAsFavoritesToDB(
        pokemonDetails: PokemonDetailsViewData,
        pokemonSpeciesSection: PokemonSpeciesSectionViewData
    ) {
        withContext(Dispatchers.IO) {
            repository.saveFavoritesPokemonToDB(
                pokemonDetails = pokemonDetails,
                pokemonSpeciesSection = pokemonSpeciesSection
            )
        }
    }

    suspend fun deleteFavoritePokemonFromDB(
        pokemonNumber: Int
    ) {
        withContext(Dispatchers.IO) {
            repository.removeFavoritePokemonFromDB(
                pokemonNumber = pokemonNumber
            )
        }
    }

    suspend fun getFavoritesPokemonFromDB(): Result<List<PokemonDetailsViewData>> = withContext(Dispatchers.IO) {
        withContext(Dispatchers.IO) {
            Result.success(repository.getAllFavoritesPokemonDB())
        }
    }

    suspend fun getPokemonInfoFromDB(
        pokemonNumber: Int
    ): Result<PokemonDetailsViewData?> {
        return withContext(Dispatchers.IO) {
            Result.success(repository.getFullPokemonInfoFromDB(pokemonNumber))
        }
    }
}