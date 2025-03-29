package com.example.healthmate

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.health.connect.client.PermissionController
import androidx.navigation.compose.rememberNavController
import com.example.healthmate.data.HealthConnectManager
import com.example.healthmate.navigation.HealthNavigation
import com.example.healthmate.ui.theme.HealthMateTheme

@Composable
fun HealthMateApp(healthConnectManager: HealthConnectManager) {
    val navController = rememberNavController()
    
    val permissionLauncher = rememberLauncherForActivityResult(
        PermissionController.createRequestPermissionResultContract()
    ) { granted ->
        if (granted.containsAll(healthConnectManager.permissions)) {
            Log.d("HealthConnect", "Permissions granted successfully!")
        } else {
            Log.d("HealthConnect", "Permissions not granted: $granted")
        }
    }
    
    LaunchedEffect(Unit) {
        val hasPermissions =
            healthConnectManager.hasAllPermissions(healthConnectManager.permissions)
        
        if (!hasPermissions) {
            Log.d("HealthConnect", "Requesting permissions...")
            permissionLauncher.launch(healthConnectManager.permissions)
        }
    }
    
    HealthMateTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HealthNavigation(
                navHostController = navController
            )
        }
    }
}