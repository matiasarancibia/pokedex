package com.matiasarancibia.pokedex.domain.model

import java.io.Serializable

data class CriesViewData(
    val legacy: String? = null,
    val latest: String? = null
): Serializable
