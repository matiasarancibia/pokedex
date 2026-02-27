package com.matiasarancibia.pokedex.ui.components

import androidx.compose.animation.core.StartOffset
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.matiasarancibia.pokedex.R
import com.matiasarancibia.pokedex.ui.theme.ScrimColor

private val CONTAINER_SIZE = 80.dp

@Composable
fun LoadingComponent(
    showDarkBackground: Boolean = false,
    isFullScreen: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = if (showDarkBackground) {
                    ScrimColor
                } else if (isFullScreen) {
                    MaterialTheme.colorScheme.background
                } else {
                    Color.Transparent
                }
            )
            .clickable(false) {
                // Prevent click through
            },
        contentAlignment = Alignment.Center
    ) {
        Card(
            border = if (!isFullScreen) {
                BorderStroke(
                    color = MaterialTheme.colorScheme.outlineVariant,
                    width = 1.dp
                )
            } else {
                null
            },
            colors = CardDefaults.cardColors(
                containerColor = if (!isFullScreen) {
                    MaterialTheme.colorScheme.surface
                } else {
                    Color.Transparent
                }
            )
        ) {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .size(CONTAINER_SIZE),
                contentAlignment = Alignment.Center
            ) {
                PulseAnimation(
                    modifier = Modifier.size(CONTAINER_SIZE),
                    color = MaterialTheme.colorScheme.primary,
                )

                PulseAnimation(
                    modifier = Modifier.size(CONTAINER_SIZE),
                    color = MaterialTheme.colorScheme.primary,
                    startOffset = StartOffset(150)
                )

                Image(
                    modifier = Modifier.height(50.dp),
                    painter = painterResource(R.drawable.ic_pokeball),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoadingComponentPreview(
    @PreviewParameter(LoadingComponentPreviewProvider::class) data: Pair<Boolean, Boolean>
) {
    val (showDarkScrim, isFullScreen) = data

    LoadingComponent(
        showDarkBackground = showDarkScrim,
        isFullScreen = isFullScreen
    )
}

private class LoadingComponentPreviewProvider : PreviewParameterProvider<Pair<Boolean, Boolean>> {
    override val values: Sequence<Pair<Boolean, Boolean>> = sequenceOf(
        false to false,
        true to false,
        false to true,
        true to true
    )
}