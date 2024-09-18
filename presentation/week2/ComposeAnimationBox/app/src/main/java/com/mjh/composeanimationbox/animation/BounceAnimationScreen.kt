package com.mjh.composeanimationbox.animation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
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
fun BounceAnimationScreen(navController: NavController) {
    AnimationScreen(
        navController = navController,
        title = "Bounce Animation",
        animationContent = { isExecuted ->
            val transition = updateTransition(targetState = isExecuted, label = "bounceTransition")

            val offsetY by transition.animateDp(
                transitionSpec = {
                    if (isExecuted) {
                        repeatable(
                            iterations = 10,
                            animation = tween(durationMillis = 500, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        )
                    } else {
                        tween(durationMillis = 500)
                    }
                },
                label = "offsetY"
            ) { state ->
                if (state) 0.dp else 50.dp
            }

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .offset(y = offsetY)
                    .background(Color.Cyan)
            )
        }
    )
}

