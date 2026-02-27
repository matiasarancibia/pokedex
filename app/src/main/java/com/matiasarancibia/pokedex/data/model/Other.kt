package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class Other(

	@SerializedName("dream_world")
	val dreamWorld: DreamWorld? = null,

	@SerializedName("showdown")
	val showdown: Showdown? = null,

	@SerializedName("official-artwork")
	val officialArtwork: OfficialArtwork? = null,

	@SerializedName("home")
	val home: Home? = null
)