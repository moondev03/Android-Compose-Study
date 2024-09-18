package com.mjh.composeanimationbox.animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mjh.composeanimationbox.AnimationScreen

@Composable
fun ColorChangeAnimationScreen(navController: NavController) {
    AnimationScreen(
        navController = navController,
        title = "Color Change Animation",
        animationContent = { isExecuted ->
            val color by animateColorAsState(
                targetValue = if (isExecuted) Color.Magenta else Color.Yellow,
                animationSpec = tween(durationMillis = 1000), label = ""
            )

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(color)
            )
        }
    )
}
