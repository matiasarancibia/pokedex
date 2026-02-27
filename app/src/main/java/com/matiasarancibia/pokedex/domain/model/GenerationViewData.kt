package com.matiasarancibia.pokedex.domain.model

import java.io.Serializable

data class GenerationViewData(
    val name: String? = null,
    val url: String? = null
): Serializable
