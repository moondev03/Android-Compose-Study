package com.mjh.composeanimationbox.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mjh.composeanimationbox.AnimationScreen
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun FillingBoxAnimationScreen(navController: NavController) {
    AnimationScreen(navController = navController, title = "Filling Box Animation", animationContent = { isExecuted ->
        if (isExecuted) {
            FillingBoxAnimationContent()
        } else {
            Text("Start the animation")
        }
    })
}

@Composable
fun FillingBoxAnimationContent(){
    val waveColor = Color.Blue
    val waveAmplitude = 20f
    val wavePeriod = 400f

    var animationProgress by remember { mutableFloatStateOf(0f) }
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    LaunchedEffect(progress) {
        animationProgress = progress
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val wavePath = Path()

        val fillHeight = canvasHeight * animationProgress
        val waveTop = canvasHeight - fillHeight

        wavePath.moveTo(0f, canvasHeight)

        for (x in 0..canvasWidth.toInt() step 10) {
            val y = waveTop + sin((x / wavePeriod + animationProgress * 2) * 2 * PI.toFloat()) * waveAmplitude
            wavePath.lineTo(x.toFloat(), y)
        }

        wavePath.lineTo(canvasWidth, canvasHeight)
        wavePath.close()

        // Create gradient for 3D effect
        val gradient = Brush.verticalGradient(
            colors = listOf(
                waveColor.copy(alpha = 0.7f),
                waveColor
            ),
            startY = waveTop,
            endY = canvasHeight
        )

        // Draw main wave with gradient
        drawPath(
            path = wavePath,
            brush = gradient
        )

        // Add highlights
        val highlightPath = Path()
        highlightPath.moveTo(0f, waveTop)
        for (x in 0..canvasWidth.toInt() step 10) {
            val y = waveTop + sin((x / wavePeriod + animationProgress * 2) * 2 * PI.toFloat()) * waveAmplitude * 0.9f
            highlightPath.lineTo(x.toFloat(), y)
        }

        drawPath(
            path = highlightPath,
            color = Color.White.copy(alpha = 0.3f),
            style = Stroke(width = 2.dp.toPx())
        )

        // Add shadow for depth
        val shadowPath = Path()
        shadowPath.moveTo(0f, waveTop + waveAmplitude * 2)
        for (x in 0..canvasWidth.toInt() step 10) {
            val y = waveTop + waveAmplitude * 2 + sin((x / wavePeriod + animationProgress * 2) * 2 * PI.toFloat()) * waveAmplitude * 0.5f
            shadowPath.lineTo(x.toFloat(), y)
        }

        drawPath(
            path = shadowPath,
            color = Color.Black.copy(alpha = 0.2f),
            style = Stroke(width = 4.dp.toPx(), pathEffect = PathEffect.cornerPathEffect(10f))
        )
    }
}
