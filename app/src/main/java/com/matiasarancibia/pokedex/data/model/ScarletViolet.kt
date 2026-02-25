package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class ScarletViolet(

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_female")
	val frontFemale: Any? = null
)