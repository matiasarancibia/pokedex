package com.matiasarancibia.pokedex.ui.util.extensions

import com.matiasarancibia.pokedex.domain.model.APIErrorViewData

fun APIErrorViewData.is401Error() = this.errorCode == "401"

fun APIErrorViewData.is404Error() = this.errorCode == "404"

fun APIErrorViewData.is500Error() = this.errorCode == "500"