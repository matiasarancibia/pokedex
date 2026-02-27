package com.matiasarancibia.pokedex.domain.model

import com.matiasarancibia.pokedex.ui.util.extensions.empty
import java.io.Serializable

data class StatsViewData(
    val name: String = String.empty(),
    val url: String? = null
): Serializable