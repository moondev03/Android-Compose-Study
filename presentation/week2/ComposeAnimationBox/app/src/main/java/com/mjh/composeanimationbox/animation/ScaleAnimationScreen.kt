package com.mjh.composeanimationbox.animation

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mjh.composeanimationbox.AnimationScreen


@Composable
fun ScaleAnimationScreen(navController: NavController) {
    AnimationScreen(
        navController = navController,
        title = "Scale Animation",
        animationContent = { isExecuted ->
            val transition = updateTransition(targetState = isExecuted, label = "scaleTransition")
            val animatedScale by transition.animateFloat(
                transitionSpec = { tween(durationMillis = 1000) },
                label = "animatedScale"
            ) { state ->
                if (state) 2f else 1f
            }

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .scale(animatedScale)
                    .background(Color.Green)
            )
        }
    )
}
