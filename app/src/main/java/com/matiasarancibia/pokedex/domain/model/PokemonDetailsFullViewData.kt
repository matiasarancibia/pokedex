package com.matiasarancibia.pokedex.domain.model

import androidx.annotation.DrawableRes
import com.matiasarancibia.pokedex.ui.util.PokemonTypes
import com.matiasarancibia.pokedex.ui.util.extensions.empty
import java.io.Serializable

data class PokemonDetailsFullViewData(
    val name: String? = null, // The name of the Pokemon
    val number: Int? = null,
    val cries: CriesViewData? = null,
    val types: List<PokemonTypes> = emptyList(),
    val typeNamesList: List<String> = emptyList(),
    val typesUrl: List<String?> = emptyList(),
    val officialArtworkImageUrl: String = String.empty(),
    val shinyImageUrl: String = String.empty(),
    val frontImageUrl: String = String.empty(),
    val backImageUrl: String = String.empty(),
    val weight: String = String.empty(),
    val height: String = String.empty(),
    val baseExperience: String = String.empty(),
    @DrawableRes var fakePokemonImageRes: Int = -1,
    val stats: List<StatsItemViewData> = emptyList(),
    val pokedexEntryText: String? = null,
    val pokemonNamesText: String? = null,
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
) : Serializable