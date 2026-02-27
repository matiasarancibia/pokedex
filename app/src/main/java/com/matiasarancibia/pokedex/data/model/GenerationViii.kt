package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class GenerationViii(

	@SerializedName("brilliant-diamond-shining-pearl")
	val brilliantDiamondShiningPearl: BrilliantDiamondShiningPearl? = null,

	@SerializedName("icons")
	val icons: Icons? = null
)