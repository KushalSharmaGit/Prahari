package com.example.prahari

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.prahari.navigation.AppNavigation
import com.example.prahari.ui.theme.PrahariTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            PrahariTheme {

                AppNavigation()

            }

        }

    }

}