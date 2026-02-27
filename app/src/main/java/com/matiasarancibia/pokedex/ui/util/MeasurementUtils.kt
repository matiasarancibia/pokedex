package com.matiasarancibia.pokedex.ui.util

import android.content.res.Resources
import android.util.TypedValue
import androidx.compose.ui.unit.Dp

fun Dp.toPx() = dpToPx(this.value)

fun dpToPx(dp: Float) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp,
    Resources.getSystem().displayMetrics
).toInt()