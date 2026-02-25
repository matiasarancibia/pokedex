package com.matiasarancibia.pokedex.ui.util // Or your preferred package

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

// This class is used to manage the shared preferences of the application
class SharedPreferencesManager(context: Context) {

    // Initialize SharedPreferences instance
    // MODE_PRIVATE means the file can only be accessed by the calling application
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFS_FILE_NAME,
        Context.MODE_PRIVATE
    )

    // Companion object to hold constants for keys and file name
    companion object {
        private const val PREFS_FILE_NAME = "com.matiasarancibia.pokedex.APP_SHARED_PREFERENCES"

        // Here we define the keys that we will use in the shared preferences manager
        const val KEY_USER = "user"
    }

    /*
        These functions are used to store and retrieve data from the shared preferences
        using the keys defined above
     */
    fun clearUser() {
        sharedPreferences.edit { remove(KEY_USER) }
    }

    fun clearAllPreferences() {
        sharedPreferences.edit { clear() }
    }
}
