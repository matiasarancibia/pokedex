package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class GenerationV(

	@SerializedName("black-white")
	val blackWhite: BlackWhite? = null
)