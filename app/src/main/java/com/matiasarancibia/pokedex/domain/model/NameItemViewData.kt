package com.matiasarancibia.pokedex.domain.model

import java.io.Serializable

data class NameItemViewData(
    val language: LanguageViewData? = null,
    val name: String? = null
): Serializable
