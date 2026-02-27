package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class Yellow(

	@SerializedName("front_gray")
	val frontGray: String? = null,

	@SerializedName("back_transparent")
	val backTransparent: String? = null,

	@SerializedName("back_default")
	val backDefault: String? = null,

	@SerializedName("back_gray")
	val backGray: String? = null,

	@SerializedName("front_default")
	val frontDefault: String? = null,

	@SerializedName("front_transparent")
	val frontTransparent: String? = null
)