package com.matiasarancibia.pokedex.domain.model

import java.io.Serializable

data class PokemonEntryItemViewData(
    val entryNumber: Int? = null,
    val pokemonSpecies: PokemonSpeciesViewData? = null
): Serializable
