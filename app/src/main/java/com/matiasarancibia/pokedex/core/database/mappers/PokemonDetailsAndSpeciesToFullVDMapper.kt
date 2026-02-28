package com.matiasarancibia.pokedex.core.database.mappers

import com.matiasarancibia.pokedex.domain.model.PokemonDetailsFullViewData
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.domain.model.PokemonSpeciesSectionViewData
import javax.inject.Inject

class PokemonDetailsAndSpeciesToFullVDMapper @Inject constructor() {

    fun executeMapping(
        pokemonDetailsVD: PokemonDetailsViewData?,
        pokemonSpeciesSectionVD: PokemonSpeciesSectionViewData?
    ): PokemonDetailsFullViewData? {
        return if (pokemonDetailsVD != null && pokemonSpeciesSectionVD != null) {
            PokemonDetailsFullViewData(
                name = pokemonDetailsVD.name,
                number = pokemonDetailsVD.number,
                cries = pokemonDetailsVD.cries,
                types = pokemonDetailsVD.types,
                typeNamesList = pokemonDetailsVD.typeNamesList,
                typesUrl = pokemonDetailsVD.typesUrl,
                officialArtworkImageUrl = pokemonDetailsVD.officialArtworkImageUrl,
                shinyImageUrl = pokemonDetailsVD.shinyImageUrl,
                frontImageUrl = pokemonDetailsVD.frontImageUrl,
                backImageUrl = pokemonDetailsVD.backImageUrl,
                weight = pokemonDetailsVD.weight,
                height = pokemonDetailsVD.height,
                baseExperience = pokemonDetailsVD.baseExperience,
                stats = pokemonDetailsVD.stats,
                id = pokemonSpeciesSectionVD.id,
                captureRate = pokemonSpeciesSectionVD.captureRate,
                hasGenderDifferences = pokemonSpeciesSectionVD.hasGenderDifferences,
                isBaby = pokemonSpeciesSectionVD.isBaby,
                isLegendary = pokemonSpeciesSectionVD.isLegendary,
                isMythical = pokemonSpeciesSectionVD.isMythical,
                baseHappiness = pokemonSpeciesSectionVD.baseHappiness,
                evolutionChain = pokemonSpeciesSectionVD.evolutionChain,
                generation = pokemonSpeciesSectionVD.generation,
                growthRate = pokemonSpeciesSectionVD.growthRate,
                flavorTextEntries = pokemonSpeciesSectionVD.flavorTextEntries,
                names = pokemonSpeciesSectionVD.names
            )
        } else {
            null
        }
    }
}