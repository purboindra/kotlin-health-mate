package com.example.healthmate.ui.screen.main

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.example.healthmate.ui.component.DateRangePickerModal
import com.example.healthmate.ui.component.ExpandableFAB
import com.example.healthmate.ui.navigation.Screen
import com.example.healthmate.ui.screen.exercise.ExerciseScreen
import com.example.healthmate.ui.screen.goal.GoalScreen
import com.example.healthmate.ui.screen.home.HomeScreen
import com.example.healthmate.ui.screen.profile.ProfileScreen
import com.example.healthmate.util.VerticalSpacer
import com.example.healthmate.util.millisToLocaleDate
import kotlinx.coroutines.launch
import java.time.temporal.ChronoUnit

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    navHostController: NavHostController,
    healthConnectManager: HealthConnectManager
) {
    
    val context = LocalContext.current
    
    val coroutineScope = rememberCoroutineScope()
    
    val bottomNavController = rememberNavController()
    val currentDestination =
        bottomNavController.currentBackStackEntryAsState().value?.destination?.route
    
    var showDateRangeModal by remember { mutableStateOf(false) }
    
    if (showDateRangeModal) {
        DateRangePickerModal(
            onDismiss = {
                Log.d("MainScreen", "onDismiss DateRangePickerModal")
                showDateRangeModal = false
            },
            onDateRangeSelected = {
                Log.d("MainScreen", "Selected date: $it")
                
                val startDate = it.first?.millisToLocaleDate()
                val endDate = it.second?.millisToLocaleDate()
                
                val daysBetween = ChronoUnit.DAYS.between(startDate, endDate)
                
                Log.d("MainScreen", "Days between: $daysBetween")
                
                if (daysBetween > 7) {
                    Toast.makeText(
                        context,
                        "Date range cannot be more than 7 days",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (daysBetween < 7) {
                    Toast.makeText(
                        context,
                        "Date range cannot be less than 7 days",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    coroutineScope.launch {
                        healthConnectManager.writeWeeklyPlanExercise(startDate!!)
                    }
                }
                
                showDateRangeModal = false
            }
        )
    }
    
    Scaffold(
        floatingActionButton = {
            if (currentDestination == "/exercise")
                ExpandableFAB(
                    content = {
                        Column {
                            ElevatedButton(
                                onClick = {
                                    navHostController.navigate(Screen.Walk.route)
                                }
                            ) {
                                Text("Walking")
                            }
                            4.VerticalSpacer()
                            ElevatedButton(
                                onClick = {
                                    showDateRangeModal = true
                                }
                            ) {
                                Text("Set Goal")
                            }
                        }
                    }
                )
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
                    navHostController = navHostController,
                    healthConnectManager = healthConnectManager,
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