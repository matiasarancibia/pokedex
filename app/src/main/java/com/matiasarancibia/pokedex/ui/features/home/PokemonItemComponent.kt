package com.matiasarancibia.pokedex.ui.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.ui.theme.PokedexTheme
import com.matiasarancibia.pokedex.ui.theme.shapes.MediumRoundedCornerShape
import com.matiasarancibia.pokedex.ui.util.extensions.onClick
import com.matiasarancibia.pokedex.ui.util.helpers.PokemonItemHelper

@Composable
fun PokemonItemComponent(
    modifier: Modifier = Modifier,
    data: PokemonDetailsViewData,
    onItemClick: (PokemonDetailsViewData) -> Unit = {}
) {
    val isInPreview = LocalInspectionMode.current

    OutlinedCard(
        modifier = modifier
            .size(120.dp)
            .onClick(
                shape = MediumRoundedCornerShape,
                onClick = {
                    onItemClick(data)
                }
            ),
        shape = MediumRoundedCornerShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            BasicText(
                modifier = Modifier.align(Alignment.End),
                text = "#${data.formattedNumber}",
                maxLines = 1,
                autoSize = TextAutoSize.StepBased(
                    minFontSize = 8.sp,
                    maxFontSize = 16.sp
                ),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontSize = 8.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Light
                )
            )

            if (isInPreview) {
                Image(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .weight(1f),
                    painter = painterResource(data.fakePokemonImageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            } else {
                AsyncImage(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .weight(1f),
                    model = data.officialArtworkImageUrl,
                    contentDescription = "Pokemon Image",
                    contentScale = ContentScale.Fit
                )
            }

            BasicText(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = data.formattedName,
                maxLines = 1,
                autoSize = TextAutoSize.StepBased(
                    minFontSize = 8.sp,
                    maxFontSize = 16.sp
                ),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonItemComponentPreview(
    @PreviewParameter(PokemonItemProvider::class) pokemonList: PokemonDetailsViewData
) {
    PokedexTheme {
        PokemonItemComponent(
            modifier = Modifier.padding(5.dp),
            data = pokemonList
        )
    }
}

private class PokemonItemProvider : PreviewParameterProvider<PokemonDetailsViewData> {
    override val values: Sequence<PokemonDetailsViewData> = PokemonItemHelper.createPokemonDetailsList().asSequence()
}