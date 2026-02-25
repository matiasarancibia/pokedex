package com.matiasarancibia.pokedex.ui.util.extensions

fun Boolean?.isTrue(): Boolean {
    return this == true
}

fun Boolean?.isNullOrFalse(): Boolean {
    return ((this == null).or(this == false))
}

fun Boolean?.orFalse(): Boolean {
    return !this.isNullOrFalse()
}

fun Boolean?.orTrue(): Boolean {
    return this ?: true
}

fun <T> T?.orElse(otherValue: T): T {
    return this ?: otherValue
}