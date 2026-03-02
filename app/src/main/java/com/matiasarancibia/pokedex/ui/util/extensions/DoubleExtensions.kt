package com.matiasarancibia.pokedex.ui.util.extensions

import java.text.DecimalFormat

fun Double?.formatDecimal(
    maxDecimals: Int = 2
): String {
    val pattern = "#." + "#".repeat(maxDecimals)

    return this?.let {
        DecimalFormat(pattern).format(it).replace(".", ",")
    }.orEmpty()
}