package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class Versions(

	@SerializedName("generation-iii")
	val generationIii: GenerationIii? = null,

	@SerializedName("generation-ix")
	val generationIx: GenerationIx? = null,

	@SerializedName("generation-ii")
	val generationIi: GenerationIi? = null,

	@SerializedName("generation-v")
	val generationV: GenerationV? = null,

	@SerializedName("generation-iv")
	val generationIv: GenerationIv? = null,

	@SerializedName("generation-vii")
	val generationVii: GenerationVii? = null,

	@SerializedName("generation-i")
	val generationI: GenerationI? = null,

	@SerializedName("generation-viii")
	val generationViii: GenerationViii? = null,

	@SerializedName("generation-vi")
	val generationVi: GenerationVi? = null
)