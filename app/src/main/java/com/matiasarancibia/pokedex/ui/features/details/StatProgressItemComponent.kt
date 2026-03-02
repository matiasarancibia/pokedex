package com.matiasarancibia.pokedex.ui.features.details

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matiasarancibia.pokedex.domain.model.StatsItemViewData
import com.matiasarancibia.pokedex.domain.model.StatsViewData
import com.matiasarancibia.pokedex.ui.theme.GrassTypeColor
import com.matiasarancibia.pokedex.ui.theme.PokedexTheme
import com.matiasarancibia.pokedex.ui.theme.Purple40
import com.matiasarancibia.pokedex.ui.theme.shapes.LargeRoundedCornerShape

@Composable
fun StatProgressItem(
    statItem: StatsItemViewData,
    brush: Brush
) {
    val progress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = statItem.baseStat / 255f, // 255 is the max value of a pokemon stat
            animationSpec = tween(durationMillis = 800, delayMillis = 500)
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        statItem.stat?.let { stat ->
            // Stat name
            BasicText(
                modifier = Modifier.weight(0.2f),
                text = stat.name,
                maxLines = 1,
                autoSize = TextAutoSize.StepBased(
                    minFontSize = 8.sp,
                    maxFontSize = MaterialTheme.typography.bodySmall.fontSize
                ),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )

            // Progress bar simulated with a Box
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .weight(1f)
                    .clip(LargeRoundedCornerShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress.value)
                        .animateContentSize()
                        .clip(LargeRoundedCornerShape)
                        .fillMaxHeight()
                        .background(brush = brush)
                )
            }

            // Stat value
            Text(
                modifier = Modifier.weight(0.2f),
                text = statItem.baseStat.toString(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StatProgressItemPreview() {
    PokedexTheme {
        StatProgressItem(
            statItem = StatsItemViewData(
                baseStat = 240,
                effort = 0,
                stat = StatsViewData(
                    name = "hp",
                    url = ""
                )
            ),
            brush = Brush.linearGradient(colors = listOf(GrassTypeColor, Purple40))
        )
    }
}