package com.matiasarancibia.pokedex.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screens {

    @Serializable
    object HomeScreen : Screens()

    @Serializable
    object FavoritesScreen : Screens()

    @Serializable
    object ProfileScreen : Screens()
}
