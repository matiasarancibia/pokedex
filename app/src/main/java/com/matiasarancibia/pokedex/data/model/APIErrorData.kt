package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

class APIErrorData {

    @field:SerializedName("errorTitle")
    val errorTitle: String? = null

    @field:SerializedName("errorMessage", alternate = ["errorMsg", "message"])
    val errorMessage: String? = null

    @field:SerializedName("localisedMessage")
    val localisedMessage: String? = null

    @field:SerializedName("errorId")
    val errorId: String? = null

    @field:SerializedName("canRefresh")
    val canRefresh: Boolean? = null

    @field:SerializedName("errorCode", alternate = ["error"])
    val errorCode: String? = null

    @field:SerializedName("statusCode")
    val httpStatusCode: String? = null
}