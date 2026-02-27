package com.matiasarancibia.pokedex.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    isInPreview: Boolean,
    imageUrl: String? = null,
    @DrawableRes fakeImageRes: Int? = null
) {
    if (isInPreview) {
        fakeImageRes?.let {
            Image(
                modifier = modifier,
                painter = painterResource(fakeImageRes),
                contentDescription = "Pokemon Image",
                contentScale = ContentScale.Fit
            )
        }
    } else {
        AsyncImage(
            modifier = modifier,
            model = imageUrl,
            contentDescription = "Pokemon Image",
            contentScale = ContentScale.Fit
        )
    }
}