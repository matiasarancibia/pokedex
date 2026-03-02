package com.matiasarancibia.pokedex.ui.util.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Locale

inline fun <R> String.letNotEmpty(block: (String) -> R): R? {
    return if (this.isNotBlank()) {
        block(this)
    } else
        null
}

fun String.Companion.empty() = ""

@Suppress("FunctionName")
fun String.Companion.UTF8Encoding() = "UTF-8"

fun String.getPlural(): String {
    return if (this.endsWith("y", true)) {
        this.lowercase().plus("ies")
    } else if (this.endsWith("s", true)) {
        this.lowercase().plus("es")
    } else {
        this.lowercase().plus("s")
    }
}

fun String?.capitalizeWord(): String? {
    return this?.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
}

fun String.toHashMap(): HashMap<String, Any> {
    if (this.isBlank()) {
        return kotlin.collections.HashMap()
    }

    return Gson().fromJson(
        this,
        object : TypeToken<HashMap<String, Any>>() {}.type
    )
}

fun String.cleanEntryText(): String {
    return this
        .replace("\\n", " ")
        .replace("\\f", " ")
        .replace("\\r", " ")
        .replace("\n", " ")
        .replace("\u000c", " ")
        .replace("\r", " ")
        .replace("  ", " ")
}