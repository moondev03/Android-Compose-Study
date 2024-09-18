package com.mjh.animationcodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mjh.animationcodelab.ui.AnimationCodelabTheme
import com.example.android.codelab.animation.ui.home.Home

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationCodelabTheme {
                Home()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    AnimationCodelabTheme {
        Home()
    }
}
