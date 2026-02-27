package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

class PokemonListResponseData {

    @SerializedName("count")
    val count: Int? = null

    @SerializedName("next")
    val next: String? = null

    @SerializedName("previous")
    val previous: String? = null

    @SerializedName("results")
    val results: List<PokemonItemData?>? = null
}