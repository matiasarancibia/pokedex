package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class GenerationIv(

	@SerializedName("platinum")
	val platinum: Platinum? = null,

	@SerializedName("diamond-pearl")
	val diamondPearl: DiamondPearl? = null,

	@SerializedName("heartgold-soulsilver")
	val heartgoldSoulsilver: HeartgoldSoulsilver? = null
)