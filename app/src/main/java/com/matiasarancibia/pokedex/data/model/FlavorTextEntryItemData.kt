package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class FlavorTextEntryItemData(

    @SerializedName("flavor_text")
    val flavorText: String? = null,

    @SerializedName("language")
    val language: LanguageData? = null,

    @SerializedName("version")
    val version: VersionData? = null
)
