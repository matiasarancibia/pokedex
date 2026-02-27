package com.matiasarancibia.pokedex.domain.model

import java.io.Serializable

data class StatsItemViewData(
    val stat: StatsViewData? = null,
    val baseStat: Int,
    val effort: Int
): Serializable