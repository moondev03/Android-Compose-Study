package com.mjh.composeanimationbox.animation

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mjh.composeanimationbox.AnimationScreen

@Composable
fun SpringAnimationScreen(navController: NavController) {
    AnimationScreen(
        navController = navController,
        title = "Spring Animation",
        animationContent = { isExecuted ->
            val infiniteTransition = rememberInfiniteTransition(label = "")

            // Dramatic damping ratio effect
            val dampingRatio = infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 500), // Faster for more dramatic effect
                    repeatMode = RepeatMode.Reverse
                ),
                label = ""
            )

            val offsetY by animateDpAsState(
                targetValue = if (isExecuted) 100.dp else 0.dp,
                animationSpec = if (isExecuted) {
                    spring(
                        dampingRatio = Spring.DampingRatioHighBouncy +
                                (Spring.DampingRatioLowBouncy - Spring.DampingRatioHighBouncy) * dampingRatio.value,
                        stiffness = Spring.StiffnessHigh // Increased stiffness for more dramatic bounce
                    )
                } else {
                    spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                },
                label = ""
            )

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .offset(y = offsetY)
                    .background(Color.Cyan)
            )
        }
    )
}
