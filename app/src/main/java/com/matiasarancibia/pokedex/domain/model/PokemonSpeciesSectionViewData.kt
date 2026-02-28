package com.matiasarancibia.pokedex.domain.model

import java.io.Serializable

data class PokemonSpeciesSectionViewData(
    val id: Int? = null,
    val baseHappiness: Int? = null,
    val captureRate: Int? = null,
    val evolutionChain: EvolutionChainViewData? = null,
    val flavorTextEntries: List<FlavorTextEntryItemViewData> = emptyList(),
    val names: List<NameItemViewData> = emptyList(),
    val generation: GenerationViewData? = null,
    val growthRate: GrowthRateViewData? = null,
    val hasGenderDifferences: Boolean,
    val isBaby: Boolean,
    val isLegendary: Boolean,
    val isMythical: Boolean
): Serializable