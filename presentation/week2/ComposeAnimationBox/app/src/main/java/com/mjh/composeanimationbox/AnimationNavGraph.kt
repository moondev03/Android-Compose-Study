package com.mjh.composeanimationbox

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mjh.composeanimationbox.animation.BounceAnimationScreen
import com.mjh.composeanimationbox.animation.ColorChangeAnimationScreen
import com.mjh.composeanimationbox.animation.FadeInAnimationScreen
import com.mjh.composeanimationbox.animation.FillingBoxAnimationScreen
import com.mjh.composeanimationbox.animation.ParallaxAnimationScreen
import com.mjh.composeanimationbox.animation.RotateAnimationScreen
import com.mjh.composeanimationbox.animation.ScaleAnimationScreen
import com.mjh.composeanimationbox.animation.SlideInAnimationScreen
import com.mjh.composeanimationbox.animation.SpringAnimationScreen
import com.mjh.composeanimationbox.animation.WavesAnimationScreen

@Composable
fun AnimationNavGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {

        composable("home") { HomeScreen(navController) }
        composable(AnimationRoute.FadeInAnimationScreen.name) { FadeInAnimationScreen(navController) }
        composable(AnimationRoute.ScaleAnimationScreen.name) { ScaleAnimationScreen(navController) }
        composable(AnimationRoute.RotateAnimationScreen.name) { RotateAnimationScreen(navController) }
        composable(AnimationRoute.SlideInAnimationScreen.name) { SlideInAnimationScreen(navController) }
        composable(AnimationRoute.BounceAnimationScreen.name) { BounceAnimationScreen(navController) }
        composable(AnimationRoute.ColorChangeAnimationScreen.name) { ColorChangeAnimationScreen(navController) }
        composable(AnimationRoute.SpringAnimationScreen.name) { SpringAnimationScreen(navController) }
        composable(AnimationRoute.ParallaxAnimationScreen.name) { ParallaxAnimationScreen(navController) }
        composable(AnimationRoute.WavesAnimationScreen.name) { WavesAnimationScreen(navController) }
        composable(AnimationRoute.FillingBoxAnimationScreen.name) { FillingBoxAnimationScreen(navController) }

    }
}