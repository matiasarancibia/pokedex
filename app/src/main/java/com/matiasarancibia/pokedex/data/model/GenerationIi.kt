package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class GenerationIi(

	@SerializedName("gold")
	val gold: Gold? = null,

	@SerializedName("crystal")
	val crystal: Crystal? = null,

	@SerializedName("silver")
	val silver: Silver? = null
)