package com.matiasarancibia.pokedex.ui.features.details

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matiasarancibia.pokedex.R
import com.matiasarancibia.pokedex.ui.theme.PokedexTheme

@Composable
fun InfoItemComponent(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    iconRotationDegrees: Float = 0f,
    imageVector: ImageVector? = null,
    @DrawableRes iconResId: Int? = null,
    tint: Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (imageVector != null) {
                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .rotate(iconRotationDegrees),
                    imageVector = imageVector,
                    contentDescription = null,
                    tint = tint
                )
            } else {
                iconResId?.let {
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .rotate(iconRotationDegrees),
                        painter = painterResource(it),
                        contentDescription = null,
                        tint = tint
                    )
                }
            }

            BasicText(
                text = value,
                maxLines = 1,
                autoSize = TextAutoSize.StepBased(
                    minFontSize = 8.sp,
                    maxFontSize = MaterialTheme.typography.bodySmall.fontSize
                ),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 11.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun InfoItemComponentPreview() {
    PokedexTheme {
        InfoItemComponent(
            title = stringResource(R.string.pokemon_weight),
            value = "69 lbs.",
            iconResId = R.drawable.ic_weight,
            tint = Color.Gray,
        )
    }
}