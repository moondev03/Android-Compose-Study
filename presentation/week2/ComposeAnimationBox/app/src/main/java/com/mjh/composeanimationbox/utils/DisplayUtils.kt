package com.mjh.composeanimationbox.utils

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Int.toDp(density: Float): Dp {
    return (this / density).toInt().dp
}

fun Float.toPx(density: Float): Float {
    return (this * density)
}