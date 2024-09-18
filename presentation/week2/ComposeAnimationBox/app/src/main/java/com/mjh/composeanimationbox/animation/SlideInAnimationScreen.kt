package com.mjh.composeanimationbox.animation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mjh.composeanimationbox.AnimationScreen

@Composable
fun SlideInAnimationScreen(navController: NavController) {
    AnimationScreen(
        navController = navController,
        title = "Slide-In Animation",
        animationContent = { isExecuted ->
            val offsetX by animateDpAsState(
                targetValue = if (isExecuted) 0.dp else 200.dp,
                animationSpec = tween(durationMillis = 1000), label = ""
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .offset(x = offsetX)
                    .background(Color.Cyan)
            )
        }
    )
}