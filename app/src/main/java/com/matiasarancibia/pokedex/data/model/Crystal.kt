package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class Crystal(

	@SerializedName("back_transparent")
	val backTransparent: String? = null,

	@SerializedName("back_shiny_transparent")
	val backShinyTransparent: String? = null,

	@SerializedName("back_default")
	val backDefault: String? = null,

	@SerializedName("front_default")
	val frontDefault: String? = null,

	@SerializedName("front_transparent")
	val frontTransparent: String? = null,

	@SerializedName("front_shiny_transparent")
	val frontShinyTransparent: String? = null,

	@SerializedName("back_shiny")
	val backShiny: String? = null,

	@SerializedName("front_shiny")
	val frontShiny: String? = null
)