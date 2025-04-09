package com.example.healthmate.ui.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("/splash")
    data object Main : Screen("/main")
    data object Home : Screen("/home")
    data object Goal : Screen("/goal")
    data object Exercise : Screen("/exercise")
    data object Profile : Screen("/profile")
    data object ExerciseOption : Screen("/exercise-option")
    data object Walk : Screen("/walk")
    data object HealthConnectUnavailable : Screen("/health-connect-unavailable")
    data object MyActivity : Screen("/my-activity")
}