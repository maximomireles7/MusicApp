package com.example.musicmax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.musicmax.ui.theme.MusicMaxTheme
import com.example.musicmax.navigation.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicMaxTheme {
                NavGraph()
            }
        }
    }
}
