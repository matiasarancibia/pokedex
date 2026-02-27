package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class DiamondPearl(

	@SerializedName("back_shiny_female")
	val backShinyFemale: Any? = null,

	@SerializedName("back_female")
	val backFemale: Any? = null,

	@SerializedName("back_default")
	val backDefault: String? = null,

	@SerializedName("front_shiny_female")
	val frontShinyFemale: Any? = null,

	@SerializedName("front_default")
	val frontDefault: String? = null,

	@SerializedName("front_female")
	val frontFemale: Any? = null,

	@SerializedName("back_shiny")
	val backShiny: String? = null,

	@SerializedName("front_shiny")
	val frontShiny: String? = null
)