package com.matiasarancibia.pokedex.ui.util

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.BoolRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes

// This class is used to get the needed resources like String values from string resources
class AppResourcesManager(private val context: Context) {

    fun getString(@StringRes id: Int): String {
        return context.getString(id)
    }

    fun getString(id: Int, vararg formatArgs: Any?): String {
        return context.getString(id, *formatArgs)
    }

    fun getInteger(@IntegerRes id: Int): Int {
        return context.resources.getInteger(id)
    }

    fun getBoolean(@BoolRes id: Int): Boolean {
        return context.resources.getBoolean(id)
    }

    fun getStringArray(@ArrayRes id: Int): Array<String> {
        return context.resources.getStringArray(id)
    }

    fun getIntArray(@ArrayRes id: Int): IntArray {
        return context.resources.getIntArray(id)
    }
}