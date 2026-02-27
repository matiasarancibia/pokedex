package com.matiasarancibia.pokedex.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.matiasarancibia.pokedex.R

// Set of Material typography styles to start with
val BaseTypography = TextStyle(
    fontFamily = FontFamily(Font(R.font.cascadia_mono)),
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
)

val Typography = Typography(
    bodyLarge = BaseTypography.copy(
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = BaseTypography.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = BaseTypography.copy(
        fontSize = 14.sp,
        letterSpacing = 0.4.sp
    ),
    titleLarge = BaseTypography.copy(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = BaseTypography.copy(
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp
    ),
    titleSmall = BaseTypography.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    headlineLarge = BaseTypography.copy(
        fontSize = 36.sp,
        fontWeight = FontWeight.Medium
    ),
    headlineMedium = BaseTypography.copy(
        fontSize = 28.sp,
        fontWeight = FontWeight.Medium
    ),
    headlineSmall = BaseTypography.copy(
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    )
    /* Other default text styles to override
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)