package com.matiasarancibia.pokedex.domain.model

import java.io.Serializable

data class SpeciesViewData(
    val name: String? = null,
    val url: String? = null
): Serializable