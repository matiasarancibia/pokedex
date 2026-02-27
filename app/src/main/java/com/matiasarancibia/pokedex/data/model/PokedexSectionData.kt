package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class PokedexSectionData(

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("names")
    val names: List<NameItemData>? = null,

    @SerializedName("pokemon_entries")
    val pokemonEntries: List<PokemonEntryItemData>? = null,
)
