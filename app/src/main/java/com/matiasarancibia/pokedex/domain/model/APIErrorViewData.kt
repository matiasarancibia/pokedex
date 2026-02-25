package com.matiasarancibia.pokedex.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.net.HttpURLConnection

@Parcelize
data class APIErrorViewData(
    var errorTitle: String? = null,
    var errorMessage: String? = null,
    val errorId: String? = null,
    var showTryAgain: Boolean = false,
    var showClose: Boolean = false,
    var showSetting: Boolean = false,
    val errorCode: String? = null,
    val httpStatusCode: String? = HttpURLConnection.HTTP_INTERNAL_ERROR.toString(),
    var realHttpStatusCode: Int? = HttpURLConnection.HTTP_INTERNAL_ERROR,
    var customActionButtonText: String? = null,
    var showCustomAction: Boolean? = false,
    var errorButtonTitle: String? = null
) : Parcelable
