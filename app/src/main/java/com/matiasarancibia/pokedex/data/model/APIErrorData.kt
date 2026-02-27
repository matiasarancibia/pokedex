package com.matiasarancibia.pokedex.data.model

import com.google.gson.annotations.SerializedName

class APIErrorData {

    @SerializedName("errorTitle")
    val errorTitle: String? = null

    @SerializedName("errorMessage", alternate = ["errorMsg", "message"])
    val errorMessage: String? = null

    @SerializedName("localisedMessage")
    val localisedMessage: String? = null

    @SerializedName("errorId")
    val errorId: String? = null

    @SerializedName("canRefresh")
    val canRefresh: Boolean? = null

    @SerializedName("errorCode", alternate = ["error"])
    val errorCode: String? = null

    @SerializedName("statusCode")
    val httpStatusCode: String? = null
}