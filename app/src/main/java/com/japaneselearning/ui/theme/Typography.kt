package com.japaneselearning.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val JapaneseTypography = FontFamily(
    fontFamily = FontFamily.Default
)

val Typography = Typography(
    bodyLarge = androidx.compose.material3.Typography.BodyLarge.copy(
        fontFamily = JapaneseTypography,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = androidx.compose.material3.Typography.BodyMedium.copy(
        fontFamily = JapaneseTypography,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = androidx.compose.material3.Typography.BodySmall.copy(
        fontFamily = JapaneseTypography,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    titleLarge = androidx.compose.material3.Typography.TitleLarge.copy(
        fontFamily = JapaneseTypography,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    titleMedium = androidx.compose.material3.Typography.TitleMedium.copy(
        fontFamily = JapaneseTypography,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    titleSmall = androidx.compose.material3.Typography.TitleSmall.copy(
        fontFamily = JapaneseTypography,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    labelLarge = androidx.compose.material3.Typography.LabelLarge.copy(
        fontFamily = JapaneseTypography,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    labelMedium = androidx.compose.material3.Typography.LabelMedium.copy(
        fontFamily = JapaneseTypography,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    labelSmall = androidx.compose.material3.Typography.LabelSmall.copy(
        fontFamily = JapaneseTypography,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp
    )
)