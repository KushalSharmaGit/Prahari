package com.example.prahari.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prahari.ui.screens.dashboard.DashboardScreen
import com.example.prahari.ui.screens.login.LoginScreen
import com.example.prahari.ui.screens.splash.SplashScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {

            SplashScreen(navController)

        }

        composable(Screen.Login.route) {

            LoginScreen(navController,
                isLoading = false,
                onLogin = { email, password -> },
                onSignUp = { })

        }

        composable(Screen.Dashboard.route) {

            DashboardScreen(navController)

        }

    }

}