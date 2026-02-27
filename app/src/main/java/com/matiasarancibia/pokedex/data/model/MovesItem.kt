package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class MovesItem(

	@SerializedName("version_group_details")
	val versionGroupDetails: List<VersionGroupDetailsItem?>? = null,

	@SerializedName("move")
	val move: Move? = null
)