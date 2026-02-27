package com.matiasarancibia.pokedex.domain.model

import androidx.annotation.DrawableRes
import com.matiasarancibia.pokedex.ui.util.PokemonTypes
import com.matiasarancibia.pokedex.ui.util.extensions.capitalizeWord
import com.matiasarancibia.pokedex.ui.util.extensions.empty
import com.matiasarancibia.pokedex.ui.util.extensions.orElse
import java.util.Locale

data class PokemonDetailsViewData(
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
    var pokemonSpecies: PokemonSpeciesSectionViewData? = null
) {
    var pokedexEntryText: String? = null
    var pokemonNamesText: String? = null
    var formattedNumber = String.format(Locale.getDefault(), "%03d", number.orElse(0))
    var formattedName = name.capitalizeWord().orEmpty()
}
