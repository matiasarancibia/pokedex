package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class GameIndicesItem(

	@SerializedName("game_index")
	val gameIndex: Int? = null,

	@SerializedName("version")
	val version: Version? = null
)