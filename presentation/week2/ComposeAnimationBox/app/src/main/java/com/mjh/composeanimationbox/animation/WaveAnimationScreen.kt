package com.mjh.composeanimationbox.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mjh.composeanimationbox.AnimationScreen

@Composable
fun WavesAnimationScreen(navController: NavController) {
    AnimationScreen(
        navController = navController,
        title = "Waves Animation",
        animationContent = { isExecuted ->
            if (isExecuted) {
                WavesAnimationContent()
            } else {
                Text("Start the animation")
            }
        }
    )
}

@Composable
fun WavesAnimationContent() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val waveSize by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = size.center
            repeat(10) { index ->
                val scale = waveSize * (index + 1) / 5
                val alpha = 1f - index * 0.2f
                val radius = 100.dp.toPx() * scale

                drawCircle(
                    color = Color.Blue.copy(alpha = 0.3f),
                    radius = radius,
                    center = center,
                    alpha = alpha
                )
            }
        }
    }
}
