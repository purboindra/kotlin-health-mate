package com.example.healthmate.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.healthmate.data.HealthConnectManager
import com.example.healthmate.ui.screen.exercise_option.ExerciseOptionScreen
import com.example.healthmate.ui.screen.main.MainScreen
import com.example.healthmate.ui.screen.walk.WalkScreen


@RequiresApi(Build.VERSION_CODES.Q)
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
        
        composable(
            route = Screen.ExerciseOption.route
        ) {
            ExerciseOptionScreen()
        }
        
        composable(
            route = Screen.Walk.route
        ) {
            WalkScreen(
                navHostController = navHostController,
                )
        }
    }
}