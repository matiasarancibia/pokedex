package com.matiasarancibia.pokedex.domain.model

import java.io.Serializable

data class PokemonSpeciesSectionViewData(
    val baseHappiness: Int? = null,
    val captureRate: Int? = null,
    val evolutionChain: EvolutionChainViewData? = null,
    val flavorTextEntries: List<FlavorTextEntryItemViewData>? = null,
    val names: List<NameItemViewData>? = null,
    val generation: GenerationViewData? = null,
    val growthRate: GrowthRateViewData? = null,
    val hasGenderDifferences: Boolean,
    val isBaby: Boolean,
    val isLegendary: Boolean,
    val isMythical: Boolean
): Serializable