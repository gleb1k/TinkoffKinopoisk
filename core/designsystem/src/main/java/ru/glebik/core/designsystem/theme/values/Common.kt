package ru.glebik.core.designsystem.theme.values

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class Colors(
    val primary: Color,
    val onPrimary: Color,

    val secondary: Color,
    val onSecondary: Color,

    val surface: Color,
    val onSurface: Color,

    val error: Color,
    val onError: Color,

    val tint: Color,
    val background: Color,
)

data class Typography(
    val header: TextStyle,
    val body: TextStyle,
    val hint: TextStyle,
    val headerBold: TextStyle,
    val bodyBold: TextStyle,
    val hintBold: TextStyle,

    val error: TextStyle,
    val topBar: TextStyle,
    val onButtonPrimary: TextStyle,
    val onButtonSecondary: TextStyle,
)

data class Padding(
    val tiny: Dp,
    val small: Dp,
    val baseMinus: Dp,
    val base: Dp,
    val basePlus: Dp
)

data class CornerShape(
    val small: CornerBasedShape,
    val medium: CornerBasedShape,
    val large: CornerBasedShape,
)