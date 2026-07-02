package com.example.prahari.navigation

sealed class Screen(val route: String) {

    data object Splash : Screen("splash")

    data object Login : Screen("login")

    data object Dashboard : Screen("dashboard")

    data object DriverMonitoring : Screen("driver_monitoring")
}