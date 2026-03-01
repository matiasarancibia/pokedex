package com.matiasarancibia.pokedex.core.database.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsFullViewData
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.domain.model.PokemonSpeciesSectionViewData
import javax.inject.Inject

class PokemonDetailsFullToDetailsVDMapper @Inject constructor() : Mapper<PokemonDetailsFullViewData?, PokemonDetailsViewData?> {

    override fun executeMapping(data: PokemonDetailsFullViewData?): PokemonDetailsViewData? {
        return data?.let {
            PokemonDetailsViewData(
                name = it.name,
                number = it.number,
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
                pokedexEntryText = it.pokedexEntryText,
                pokemonNamesText = it.pokemonNamesText
            ).apply {
                pokemonSpecies = PokemonSpeciesSectionViewData(
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
}