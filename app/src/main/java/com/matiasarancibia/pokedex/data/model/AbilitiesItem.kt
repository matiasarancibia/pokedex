package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class AbilitiesItem(

	@SerializedName("is_hidden")
	val isHidden: Boolean? = null,

	@SerializedName("ability")
	val ability: Ability? = null,

	@SerializedName("slot")
	val slot: Int? = null
)