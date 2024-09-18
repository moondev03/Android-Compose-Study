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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mjh.composeanimationbox.AnimationScreen

@Composable
fun RotateAnimationScreen(navController: NavController) {
    AnimationScreen(
        navController = navController,
        title = "Rotate Animation",
        animationContent = { isExecuted ->
            val transition = updateTransition(targetState = isExecuted, label = "rotationTransition")
            val animatedRotation by transition.animateFloat(
                transitionSpec = { tween(durationMillis = 1000) },
                label = "animatedRotation"
            ) { state ->
                if (state) 360f else 0f
            }

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .rotate(animatedRotation)
                    .background(Color.Red)
            )
        }
    )
}
