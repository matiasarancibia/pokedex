package com.matiasarancibia.pokedex.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.matiasarancibia.pokedex.domain.model.CriesViewData
import com.matiasarancibia.pokedex.domain.model.StatsItemViewData
import com.matiasarancibia.pokedex.ui.util.PokemonTypes

@Entity("pokemonDetails")
data class PokemonDetailsEntity(
    @PrimaryKey()
    val number: Int,
    val name: String,
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
    var pokedexEntryText: String,
    var pokemonNamesText: String
)