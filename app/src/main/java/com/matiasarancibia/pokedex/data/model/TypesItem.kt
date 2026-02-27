package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class TypesItem(

	@SerializedName("slot")
	val slot: Int? = null,

	@SerializedName("type")
	val type: Type? = null
)