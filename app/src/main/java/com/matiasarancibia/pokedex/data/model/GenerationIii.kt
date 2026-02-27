package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class GenerationIii(

	@SerializedName("firered-leafgreen")
	val fireredLeafgreen: FireredLeafgreen? = null,

	@SerializedName("ruby-sapphire")
	val rubySapphire: RubySapphire? = null,

	@SerializedName("emerald")
	val emerald: Emerald? = null
)