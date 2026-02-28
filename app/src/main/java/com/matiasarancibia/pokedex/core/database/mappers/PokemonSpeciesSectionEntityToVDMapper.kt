package com.matiasarancibia.pokedex.core.database.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.core.database.entities.PokemonSpeciesSectionEntity
import com.matiasarancibia.pokedex.domain.model.PokemonSpeciesSectionViewData
import javax.inject.Inject

class PokemonSpeciesSectionEntityToVDMapper @Inject constructor() : Mapper<PokemonSpeciesSectionEntity?, PokemonSpeciesSectionViewData?> {

    override fun executeMapping(data: PokemonSpeciesSectionEntity?): PokemonSpeciesSectionViewData? {
        return data?.let {
            PokemonSpeciesSectionViewData(
                id = it.id,
                captureRate = it.captureRate,
                hasGenderDifferences = it.hasGenderDifferences,
                isBaby = it.isBaby,
                isLegendary = it.isLegendary,
                isMythical = it.isMythical,
                baseHappiness = it.baseHappiness,
                evolutionChain = it.evolutionChain,
                generation = it.generation,
                growthRate = it.growthRate,
                flavorTextEntries = it.flavorTextEntries,
                names = it.names
            )
        }
    }
}