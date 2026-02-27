package com.matiasarancibia.pokedex.domain.model

data class StatsItemViewData(
    val stat: StatsViewData? = null,
    val baseStat: Int,
    val effort: Int
)