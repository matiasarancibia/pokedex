package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesSectionData(

    @SerializedName("base_happiness")
    val baseHappiness: Int? = null,

    @SerializedName("capture_rate")
    val captureRate: Int? = null,

    @SerializedName("evolution_chain")
    val evolutionChain: EvolutionChainData? = null,

    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntryItemData>? = null,

    @SerializedName("names")
    val names: List<NameItemData>? = null,

    @SerializedName("generation")
    val generation: GenerationData? = null,

    @SerializedName("growth_rate")
    val growthRate: GrowthRateData? = null,

    @SerializedName("has_gender_differences")
    val hasGenderDifferences: Boolean? = null,

    @SerializedName("is_baby")
    val isBaby: Boolean? = null,

    @SerializedName("is_legendary")
    val isLegendary: Boolean? = null,

    @SerializedName("is_mythical")
    val isMythical: Boolean? = null
)
