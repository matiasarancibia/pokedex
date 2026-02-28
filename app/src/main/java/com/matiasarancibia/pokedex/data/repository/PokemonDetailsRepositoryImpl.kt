package com.matiasarancibia.pokedex.data.repository

import com.matiasarancibia.pokedex.core.common.Result
import com.matiasarancibia.pokedex.core.database.PokedexDatabase
import com.matiasarancibia.pokedex.core.database.entities.PokemonDetailsEntity
import com.matiasarancibia.pokedex.core.database.entities.PokemonSpeciesSectionEntity
import com.matiasarancibia.pokedex.core.database.mappers.PokemonDetailsAndSpeciesToFullVDMapper
import com.matiasarancibia.pokedex.core.database.mappers.PokemonDetailsEntityToVDMapper
import com.matiasarancibia.pokedex.core.database.mappers.PokemonDetailsFullEntityToVDMapper
import com.matiasarancibia.pokedex.core.database.mappers.PokemonDetailsFullToDetailsVDMapper
import com.matiasarancibia.pokedex.core.database.mappers.PokemonDetailsFullVDToEntityMapper
import com.matiasarancibia.pokedex.core.database.mappers.PokemonDetailsVDToEntityMapper
import com.matiasarancibia.pokedex.core.database.mappers.PokemonSpeciesSectionEntityToVDMapper
import com.matiasarancibia.pokedex.core.database.mappers.PokemonSpeciesSectionVDToEntityMapper
import com.matiasarancibia.pokedex.data.model.PokedexSectionData
import com.matiasarancibia.pokedex.data.model.PokemonDetailsData
import com.matiasarancibia.pokedex.data.model.PokemonSpeciesSectionData
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.domain.model.PokemonSpeciesSectionViewData
import com.matiasarancibia.pokedex.domain.repository.PokedexApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonDetailsRepositoryImpl @Inject constructor(
    private val pokedexDatabase: PokedexDatabase,
    private val pokedexApiService: PokedexApiService,
    private val pokemonDetailsEntityToVDMapper: PokemonDetailsEntityToVDMapper,
    private val pokemonSpeciesSectionEntityToVDMapper: PokemonSpeciesSectionEntityToVDMapper,
    private val pokemonDetailsVDToEntityMapper: PokemonDetailsVDToEntityMapper,
    private val pokemonSpeciesSectionVDToEntityMapper: PokemonSpeciesSectionVDToEntityMapper,
    private val pokemonDetailsAndSpeciesToFullVDMapper: PokemonDetailsAndSpeciesToFullVDMapper,
    private val pokemonDetailsFullVDToEntityMapper: PokemonDetailsFullVDToEntityMapper,
    private val pokemonDetailsFullEntityToVDMapper: PokemonDetailsFullEntityToVDMapper,
    private val pokemonDetailsFullToDetailsVDMapper: PokemonDetailsFullToDetailsVDMapper
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
            pokedexApiService.getPokedexEntry(pokedexNumber)
        }
    }

    override suspend fun getPokemonSpecies(
        pokemonSpeciesUrl: String
    ): PokemonSpeciesSectionData {
        return withContext(Dispatchers.IO) {
            pokedexApiService.getPokemonSpecies(pokemonSpeciesUrl)
        }
    }

    // region Local DB operations

    override suspend fun savePokemonSpeciesToDB(pokemonSpecies: PokemonSpeciesSectionViewData) {
        val pokemonSpeciesSectionVD = pokemonSpeciesSectionVDToEntityMapper.executeMapping(pokemonSpecies)

        pokemonSpeciesSectionVD?.let {
            pokedexDatabase.pokemonSpeciesSectionDao.insertPokemonSpeciesSection(it)
        }
    }

    override suspend fun getStoredPokemonSpecies(pokemonNumber: Int): PokemonSpeciesSectionEntity? {
        return pokedexDatabase.pokemonSpeciesSectionDao.getPokemonSpeciesSection(pokemonNumber)
    }

    override suspend fun savePokemonDetailsToDB(pokemonDetails: PokemonDetailsViewData) {
        val pokemonDetailsVD = pokemonDetailsVDToEntityMapper.executeMapping(pokemonDetails)

        pokemonDetailsVD?.let {
            pokedexDatabase.pokemonDetailsDao.insertPokemonDetails(it)
        }
    }

    override suspend fun getStoredPokemonDetails(pokemonNumber: Int): PokemonDetailsEntity? {
        return pokedexDatabase.pokemonDetailsDao.getPokemonDetails(pokemonNumber)
    }

    override suspend fun saveFullPokemonInfoToDB(
        pokemonDetails: PokemonDetailsViewData,
        pokemonSpeciesSection: PokemonSpeciesSectionViewData
    ) {
        savePokemonDetailsToDB(pokemonDetails)
        savePokemonSpeciesToDB(pokemonSpeciesSection)
    }

    override suspend fun getFullPokemonInfoFromDB(pokemonNumber: Int): PokemonDetailsViewData? {
        val pokemonDetails = getStoredPokemonDetails(pokemonNumber)
        val pokemonSpecies = getStoredPokemonSpecies(pokemonNumber)
        val pokemonDetailsVD = pokemonDetailsEntityToVDMapper.executeMapping(pokemonDetails)
        val pokemonSpeciesSectionVD = pokemonSpeciesSectionEntityToVDMapper.executeMapping(pokemonSpecies)

        return pokemonDetailsVD?.apply {
            this.pokemonSpecies = pokemonSpeciesSectionVD
        }
    }

    override suspend fun removeFavoritePokemonFromDB(pokemonNumber: Int) {
        pokedexDatabase.favoritesDao.deleteFavoritePokemon(pokemonNumber)
    }

    override suspend fun saveFavoritesPokemonToDB(
        pokemonDetails: PokemonDetailsViewData,
        pokemonSpeciesSection: PokemonSpeciesSectionViewData
    ) {
        val pokemonDetailsFullVD = pokemonDetailsAndSpeciesToFullVDMapper.executeMapping(
            pokemonDetails,
            pokemonSpeciesSection
        )
        val pokemonDetailsFullEntity = pokemonDetailsFullVDToEntityMapper.executeMapping(
            pokemonDetailsFullVD
        )

        pokemonDetailsFullEntity?.let {
            pokedexDatabase.favoritesDao.insertFavoritePokemon(it)
        }
    }

    override suspend fun getAllFavoritesPokemonDB(): List<PokemonDetailsViewData> {
        val list = pokedexDatabase.favoritesDao.getAllFavorites().map {
            pokemonDetailsFullEntityToVDMapper.executeMapping(it)
        }

        return list.mapNotNull { pokemonDetailsFullToDetailsVDMapper.executeMapping(it) }
    }

    // endregion
}