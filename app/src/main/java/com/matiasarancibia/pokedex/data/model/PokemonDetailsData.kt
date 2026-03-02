package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

class PokemonDetailsData {

    @SerializedName("cries")
    var criesData: CriesData? = null

    @SerializedName("location_area_encounters")
    var locationAreaEncounters: String? = null

    @SerializedName("types")
    var types: List<TypesItem?>? = null

    @SerializedName("base_experience")
    var baseExperience: Int? = null

    @SerializedName("held_items")
    var heldItems: List<Any?>? = null

    @SerializedName("weight")
    var weight: Int? = null

    @SerializedName("is_default")
    var isDefault: Boolean? = null

    @SerializedName("past_types")
    var pastTypes: List<Any?>? = null

    @SerializedName("sprites")
    var sprites: Sprites? = null

    @SerializedName("past_abilities")
    var pastAbilities: List<Any?>? = null

    @SerializedName("abilities")
    var abilities: List<AbilitiesItem?>? = null

    @SerializedName("game_indices")
    var gameIndices: List<GameIndicesItem?>? = null

    @SerializedName("species")
    var species: SpeciesData? = null

    @SerializedName("stats")
    var stats: List<StatsItemData?>? = null

    @SerializedName("moves")
    var moves: List<MovesItem?>? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("forms")
    var forms: List<FormsItem?>? = null

    @SerializedName("height")
    var height: Int? = null

    @SerializedName("order")
    var order: Int? = null
}