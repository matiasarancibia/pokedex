package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class Gold(

	@SerializedName("back_default")
	val backDefault: String? = null,

	@SerializedName("front_default")
	val frontDefault: String? = null,

	@SerializedName("front_transparent")
	val frontTransparent: String? = null,

	@SerializedName("back_shiny")
	val backShiny: String? = null,

	@SerializedName("front_shiny")
	val frontShiny: String? = null
)