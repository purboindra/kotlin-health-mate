package com.example.healthmate.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.healthmate.data.HealthConnectManager
import com.example.healthmate.ui.screen.main.MainScreen

@Composable
fun HealthNavigation(
    navHostController: NavHostController
) {
    val context = LocalContext.current
    NavHost(navHostController, startDestination = Screen.Main.route) {
        val healthConnectManager = HealthConnectManager(context)
        composable(
            route = Screen.Main.route
        ) {
            MainScreen(
                navHostController = navHostController,
                healthConnectManager = healthConnectManager
            )
        }
    }
}