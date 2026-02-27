package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class StatsItemData(

	@SerializedName("stat")
	val statData: StatData? = null,

	@SerializedName("base_stat")
	val baseStat: Int? = null,

	@SerializedName("effort")
	val effort: Int? = null
)