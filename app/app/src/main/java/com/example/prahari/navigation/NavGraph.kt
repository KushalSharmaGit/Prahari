package com.example.prahari.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.prahari.ui.screens.dashboard.DashboardScreen
import com.example.prahari.ui.screens.driverMonitoring.DriverMonitoringScreen
import com.example.prahari.ui.screens.login.LoginScreen
import com.example.prahari.ui.screens.splash.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route,
        modifier = modifier
    ) {

        composable(Screen.Splash.route) {

            SplashScreen(navController)

        }

        composable(Screen.Login.route) {

            LoginScreen(navController,
                isLoading = false,
                onLogin = { email, password -> },
                onSignUp = { }
            )

        }

        composable(Screen.Dashboard.route) {

            DashboardScreen(navController)

        }

        composable(Screen.DriverMonitoring.route) {

            DriverMonitoringScreen()

        }

    }

}