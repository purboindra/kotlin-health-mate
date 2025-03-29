package com.example.healthmate

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.health.connect.client.PermissionController
import androidx.navigation.compose.rememberNavController
import com.example.healthmate.data.HealthConnectManager
import com.example.healthmate.ui.navigation.HealthNavigation
import com.example.healthmate.ui.theme.HealthMateTheme

@Composable
fun HealthMateApp(healthConnectManager: HealthConnectManager) {
    val navController = rememberNavController()
    
    val context = LocalContext.current
    
    fun showPermissionDeniedDialog() {
        AlertDialog.Builder(context)
            .setTitle("Permissions Denied")
            .setMessage("Some Health Connect permissions were not granted. Please grant the necessary permissions in settings.")
            .setPositiveButton("Open Settings") { _, _ ->
                val intent =
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data =
                    Uri.fromParts("package", context.packageName, null)
                context.startActivity(intent)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    val permissionLauncher = rememberLauncherForActivityResult(
        healthConnectManager.requestPermissionsActivityContract()
    ) { granted ->
        if (granted.containsAll(healthConnectManager.permissions)) {
            Log.d("HealthConnect", "Permissions granted successfully!")
        } else {
            Log.d("HealthConnect", "Permissions not granted: $granted")
            showPermissionDeniedDialog()
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