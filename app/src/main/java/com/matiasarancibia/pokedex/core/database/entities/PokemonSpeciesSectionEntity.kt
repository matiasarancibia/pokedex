package com.matiasarancibia.pokedex.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.matiasarancibia.pokedex.domain.model.EvolutionChainViewData
import com.matiasarancibia.pokedex.domain.model.FlavorTextEntryItemViewData
import com.matiasarancibia.pokedex.domain.model.GenerationViewData
import com.matiasarancibia.pokedex.domain.model.GrowthRateViewData
import com.matiasarancibia.pokedex.domain.model.NameItemViewData

@Entity("pokemonSpeciesSection")
data class PokemonSpeciesSectionEntity(
    @PrimaryKey
    val id: Int,
    val baseHappiness: Int,
    val captureRate: Int,
    val evolutionChain: EvolutionChainViewData?,
    val flavorTextEntries: List<FlavorTextEntryItemViewData>,
    val names: List<NameItemViewData>,
    val generation: GenerationViewData?,
    val growthRate: GrowthRateViewData?,
    val hasGenderDifferences: Boolean,
    val isBaby: Boolean,
    val isLegendary: Boolean,
    val isMythical: Boolean
)