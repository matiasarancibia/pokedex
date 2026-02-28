package com.matiasarancibia.pokedex.core.database.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.core.database.entities.PokemonSpeciesSectionEntity
import com.matiasarancibia.pokedex.domain.model.PokemonSpeciesSectionViewData
import com.matiasarancibia.pokedex.ui.util.extensions.orElse
import javax.inject.Inject

class PokemonSpeciesSectionVDToEntityMapper @Inject constructor() : Mapper<PokemonSpeciesSectionViewData?, PokemonSpeciesSectionEntity?> {

    override fun executeMapping(data: PokemonSpeciesSectionViewData?): PokemonSpeciesSectionEntity? {
        return data?.let {
            PokemonSpeciesSectionEntity(
                id = it.id.orElse(0),
                captureRate = it.captureRate.orElse(0),
                hasGenderDifferences = it.hasGenderDifferences,
                isBaby = it.isBaby,
                isLegendary = it.isLegendary,
                isMythical = it.isMythical,
                baseHappiness = it.baseHappiness.orElse(0),
                evolutionChain = it.evolutionChain,
                generation = it.generation,
                growthRate = it.growthRate,
                flavorTextEntries = it.flavorTextEntries,
                names = it.names
            )
        }
    }
}