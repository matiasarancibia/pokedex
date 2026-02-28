package com.matiasarancibia.pokedex.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.matiasarancibia.pokedex.domain.model.CriesViewData
import com.matiasarancibia.pokedex.domain.model.EvolutionChainViewData
import com.matiasarancibia.pokedex.domain.model.FlavorTextEntryItemViewData
import com.matiasarancibia.pokedex.domain.model.GenerationViewData
import com.matiasarancibia.pokedex.domain.model.GrowthRateViewData
import com.matiasarancibia.pokedex.domain.model.NameItemViewData
import com.matiasarancibia.pokedex.domain.model.StatsItemViewData
import com.matiasarancibia.pokedex.ui.util.PokemonTypes

@Entity("pokemonDetailsFull")
data class PokemonDetailsFullEntity(
    @PrimaryKey()
    val number: Int,
    val name: String?,
    val cries: CriesViewData?,
    val types: List<PokemonTypes>,
    val typeNamesList: List<String>,
    val typesUrl: List<String?>,
    val officialArtworkImageUrl: String,
    val shinyImageUrl: String,
    val frontImageUrl: String,
    val backImageUrl: String,
    val weight: String,
    val height: String,
    val baseExperience: String,
    val stats: List<StatsItemViewData>,
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