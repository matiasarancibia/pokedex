package com.matiasarancibia.pokedex.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.PostAdd
import androidx.compose.ui.graphics.vector.ImageVector
import com.matiasarancibia.pokedex.R

enum class BottomNavigationItems(
    val labelResId: Int,
    val icon: ImageVector,
    val screen: Screens
) {
    FAVORITES(R.string.bottom_navigation_favorites, Icons.Filled.Star, Screens.FavoritesScreen),
    HOME(R.string.bottom_navigation_home, Icons.Outlined.PostAdd, Screens.HomeScreen),
    PROFILE(R.string.bottom_navigation_profile, Icons.Outlined.AccountCircle, Screens.ProfileScreen);
}
