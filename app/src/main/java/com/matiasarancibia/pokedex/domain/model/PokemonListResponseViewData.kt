package com.matiasarancibia.pokedex.domain.model

data class PokemonListResponseViewData(
    val count: Int = 0,
    val next: String? = null,
    val previous: String? = null,
    val results: List<PokemonItemViewData> = emptyList()
)