package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class GenerationV(

	@field:SerializedName("black-white")
	val blackWhite: BlackWhite? = null
)