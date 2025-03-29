package com.example.healthmate.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.healthmate.ui.screen.goal.GoalScreen
import com.example.healthmate.ui.screen.home.HomeScreen
import com.example.healthmate.ui.screen.main.MainScreen

@Composable
fun HealthNavigation(
    navHostController: NavHostController
) {
    NavHost(navHostController, startDestination = Screen.Main.route) {
        composable(
            route = Screen.Main.route
        ) {
            MainScreen(navHostController = navHostController)
        }
        composable(
            route = Screen.Goal.route
        ) {
            GoalScreen()
        }
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen()
        }
    }
}