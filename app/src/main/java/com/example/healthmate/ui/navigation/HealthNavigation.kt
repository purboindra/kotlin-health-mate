package com.example.healthmate.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.healthmate.ui.screen.main.MainScreen

@Composable
fun HealthNavigation(
    navHostController: NavHostController
) {
    
    NavHost(navHostController, startDestination = "/main") {
        composable(
            route = "/main"
        ) {
            MainScreen()
        }
    }
}