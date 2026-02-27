package com.matiasarancibia.pokedex.domain.model

import java.io.Serializable

data class FlavorTextEntryItemViewData(
    val flavorText: String? = null,
    val language: LanguageViewData? = null,
    val version: VersionViewData? = null
): Serializable