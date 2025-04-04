package com.example.healthmate.ui.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.example.healthmate.data.HealthConnectManager
import com.example.healthmate.ui.component.AppBottomNavigationBar
import com.example.healthmate.ui.navigation.Screen
import com.example.healthmate.ui.screen.exercise.ExerciseScreen
import com.example.healthmate.ui.screen.goal.GoalScreen
import com.example.healthmate.ui.screen.home.HomeScreen
import com.example.healthmate.ui.screen.profile.ProfileScreen

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    navHostController: NavHostController,
    healthConnectManager: HealthConnectManager
) {
    
    val bottomNavController = rememberNavController()
    val currentDestination =
        bottomNavController.currentBackStackEntryAsState().value?.destination?.route
    
    Scaffold(
        floatingActionButton = {
            if (currentDestination == "/exercise")
                SmallFloatingActionButton(
                    onClick = { },
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.secondary
                ) {
                    Icon(Icons.Filled.Add, "Add")
                }
        },
        bottomBar = {
            AppBottomNavigationBar(
                selectedItem = currentDestination ?: Screen.Home.route,
                onSelectedItem = { route ->
                    if (route != currentDestination) {
                        bottomNavController.navigate(route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(Screen.Main.route) {
                                saveState = true
                            }
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        
        val graph = bottomNavController.createGraph(
            startDestination = Screen.Home.route,
        ) {
            composable(
                route = Screen.Goal.route
            ) {
                GoalScreen()
            }
            composable(
                route = Screen.Home.route
            ) {
                HomeScreen(modifier = Modifier.padding(paddingValues))
            }
            composable(
                route = Screen.Exercise.route
            ) {
                ExerciseScreen(
                    modifier = Modifier.padding(paddingValues),
                    navHostController = bottomNavController,
                    healthConnectManager = healthConnectManager
                )
            }
            composable(
                route = Screen.Profile.route
            ) {
                ProfileScreen(
                    modifier = Modifier.padding(paddingValues),
                    navHostController = bottomNavController
                )
            }
        }
        
        NavHost(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 12.dp),
            navController = bottomNavController,
            graph = graph
        )
    }
}