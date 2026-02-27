package com.matiasarancibia.pokedex.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.matiasarancibia.pokedex.R
import com.matiasarancibia.pokedex.ui.theme.PokedexTheme
import com.matiasarancibia.pokedex.ui.theme.shapes.FabNotchShape

@Composable
fun NotchedBottomBarWithFab(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: BottomNavigationItems,
    selectedScreen: Int
) {
    var selectedBottomNavItem: BottomNavigationItems by rememberSaveable { mutableStateOf(BottomNavigationItems.HOME) }
    var selectedIndex by remember { mutableIntStateOf(selectedBottomNavItem.ordinal) }

    val fabRadius = 34.dp

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            color = MaterialTheme.colorScheme.surface,
            shape = FabNotchShape(fabRadius)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomNavigationItems.entries.forEachIndexed { index, bottomNavItem ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .clickable(
                                /*
                                    Disable the click if the current screen is the home screen,
                                    so the user can navigate to Home screen from the FAB only
                                 */
                                enabled = bottomNavItem != BottomNavigationItems.HOME
                            ) {
                                selectedIndex = index

                                if (selectedIndex != selectedScreen) {
                                    selectedBottomNavItem = bottomNavItem

                                    navController.navigate(selectedBottomNavItem.screen) {
                                        // Avoid multiple copies of the same destination when
                                        // re-selecting the same item
                                        launchSingleTop = true
                                        // Restore state when re-selecting a previously selected item
                                        restoreState = true
                                    }
                                }
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically)
                    ) {
                        if (bottomNavItem != BottomNavigationItems.HOME) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = bottomNavItem.icon,
                                contentDescription = null,
                                tint = if (selectedScreen == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = stringResource(bottomNavItem.labelResId),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = if (selectedScreen == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }
                    }
                }
            }
        }

        // FAB centered in the notch
        FloatingActionButton(
            onClick = {
                if (selectedIndex != startDestination.ordinal) {
                    selectedIndex = startDestination.ordinal
                    selectedBottomNavItem = startDestination

                    navController.navigate(startDestination.screen) {
                        // Avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            },
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-fabRadius.value + 2).dp)
                .size(fabRadius * 2),
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = Color.White
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                painter = painterResource(R.drawable.ic_pokeball_white_flat),
                contentDescription = "Home"
            )
        }
    }
}

@Preview
@Composable
private fun NotchedBottomBarWithFabPreview() {
    PokedexTheme {
        NotchedBottomBarWithFab(
            modifier = Modifier,
            navController = rememberNavController(),
            startDestination = BottomNavigationItems.HOME,
            selectedScreen = BottomNavigationItems.HOME.ordinal
        )
    }
}