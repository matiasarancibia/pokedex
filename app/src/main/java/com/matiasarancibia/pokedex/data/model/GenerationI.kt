package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class GenerationI(

	@SerializedName("yellow")
	val yellow: Yellow? = null,

	@SerializedName("red-blue")
	val redBlue: RedBlue? = null
)