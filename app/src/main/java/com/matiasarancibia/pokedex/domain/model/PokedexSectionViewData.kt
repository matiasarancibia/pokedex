package com.matiasarancibia.pokedex.domain.model

import java.io.Serializable

data class PokedexSectionViewData(
    val id: Int? = null,
    val name: String? = null,
    val names: List<NameItemViewData>? = null,
    val pokemonEntries: List<PokemonEntryItemViewData>? = null,
): Serializable
