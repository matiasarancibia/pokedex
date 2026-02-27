package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class VersionGroupDetailsItem(

	@SerializedName("level_learned_at")
	val levelLearnedAt: Int? = null,

	@SerializedName("version_group")
	val versionGroup: VersionGroup? = null,

	@SerializedName("move_learn_method")
	val moveLearnMethod: MoveLearnMethod? = null,

	@SerializedName("order")
	val order: Any? = null
)