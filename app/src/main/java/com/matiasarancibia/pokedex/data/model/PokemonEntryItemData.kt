package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class PokemonEntryItemData(

    @SerializedName("entry_number")
    val entryNumber: Int? = null,

    @SerializedName("pokemon_species")
    val pokemonSpecies: PokemonSpeciesData
)
