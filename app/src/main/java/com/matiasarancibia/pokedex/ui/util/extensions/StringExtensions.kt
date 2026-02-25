package com.matiasarancibia.pokedex.ui.util.extensions

import androidx.core.util.PatternsCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

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

fun String.dateToMillis(
    formatPattern: String = "dd/MM/yyyy",
    timeZone: TimeZone = TimeZone.getTimeZone("UTC") // Default to UTC
): Long {
    val sdf = SimpleDateFormat(formatPattern, Locale.getDefault()).apply {
        this.timeZone = timeZone
    }

    return try {
        val date: Date? = sdf.parse(this)
        date!!.time
    } catch (e: Exception) {
        0
    }
}

fun Long?.millisToDateString(
    formatPattern: String = "dd/MM/yyyy",
    timeZone: TimeZone = TimeZone.getDefault() // Default to UTC
): String {
    val sdf = SimpleDateFormat(formatPattern, Locale.getDefault()).apply {
        this.timeZone = timeZone
    }

    return try {
        sdf.format(Date(this!!)).toString()
    } catch (e: Exception) {
        String.empty()
    }
}

fun String.isValidEmail() = PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidDecimalNumber(): Boolean {
    if (this.trim().isEmpty()) {
        return false
    }

    if (this != "-") {
        try {
            this.toDouble()
        } catch (_: NumberFormatException) {
            return false
        }
    }

    return true
}

fun String.isValidPositiveDecimalNumber(): Boolean {
    if (this.trim().isEmpty()) {
        return false
    }

    val value: Double

    try {
        value = this.toDouble()
    } catch (_: NumberFormatException) {
        return false
    }

    if (value <= 0) {
        return false
    }

    return true
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