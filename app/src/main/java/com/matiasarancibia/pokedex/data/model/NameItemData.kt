package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class NameItemData(

    @SerializedName("language")
    val language: LanguageData? = null,

    @SerializedName("name")
    val name: String? = null

)
