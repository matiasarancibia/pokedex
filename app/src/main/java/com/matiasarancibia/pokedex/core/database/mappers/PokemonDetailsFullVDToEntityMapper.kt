package com.matiasarancibia.pokedex.core.database.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.core.database.entities.PokemonDetailsFullEntity
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsFullViewData
import com.matiasarancibia.pokedex.ui.util.extensions.orElse
import javax.inject.Inject

class PokemonDetailsFullVDToEntityMapper @Inject constructor() : Mapper<PokemonDetailsFullViewData?, PokemonDetailsFullEntity?> {

    override fun executeMapping(data: PokemonDetailsFullViewData?): PokemonDetailsFullEntity? {
        return data?.let {
            PokemonDetailsFullEntity(
                name = it.name,
                number = it.number.orElse(0),
                cries = it.cries,
                types = it.types,
                typeNamesList = it.typeNamesList,
                typesUrl = it.typesUrl,
                officialArtworkImageUrl = it.officialArtworkImageUrl,
                shinyImageUrl = it.shinyImageUrl,
                frontImageUrl = it.frontImageUrl,
                backImageUrl = it.backImageUrl,
                weight = it.weight,
                height = it.height,
                baseExperience = it.baseExperience,
                stats = it.stats,
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