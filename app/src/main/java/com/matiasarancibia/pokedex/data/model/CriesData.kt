package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class CriesData(

	@SerializedName("legacy")
	val legacy: String? = null,

	@SerializedName("latest")
	val latest: String? = null
)