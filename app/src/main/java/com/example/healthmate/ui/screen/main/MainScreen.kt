package com.example.healthmate.ui.screen.main

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
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
    
    val bottomNavController = rememberNavController()
    val currentDestination =
        bottomNavController.currentBackStackEntryAsState().value?.destination?.route
    
    var showDateRangeModal by remember { mutableStateOf(false) }
    var startDate: LocalDate? by remember { mutableStateOf(null) }
    var endDate: LocalDate? by remember { mutableStateOf(null) }
    var loadingSetPlans by remember { mutableStateOf(false) }
    
    var expand by remember { mutableStateOf(false) }
    
    if (showDateRangeModal) {
        DateRangePickerModal(
            onDismiss = {
                showDateRangeModal = false
            },
            onDateRangeSelected = {
                val start = it.first?.millisToLocaleDate()
                val end = it.second?.millisToLocaleDate()
                
                val daysBetween = ChronoUnit.DAYS.between(start, end)
                
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
                    startDate = start
                    endDate = end
                    
                }
                
                showDateRangeModal = false
            }
        )
    }
    
    LaunchedEffect(startDate, endDate) {
        if (!showDateRangeModal && startDate != null && endDate != null) {
            loadingSetPlans = true
            healthConnectManager.writeWeeklyPlanExercise(startDate!!)
            delay(2000)
            loadingSetPlans = false
            startDate = null
            endDate = null
            Toast.makeText(context, "Plans set successfully", Toast.LENGTH_SHORT).show()
        }
    }
    
    Scaffold(
        floatingActionButton = {
            if (currentDestination == "/exercise")
                ExpandableFAB(
                    expand = expand,
                    setExpand = { expand = !expand },
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
                                    expand = false
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
                    loadingSetPlans = loadingSetPlans,
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