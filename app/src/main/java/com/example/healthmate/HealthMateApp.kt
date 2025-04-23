package com.example.healthmate

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.healthmate.data.HealthConnectManager
import com.example.healthmate.ui.navigation.HealthNavigation
import com.example.healthmate.ui.theme.HealthMateTheme

@RequiresApi(Build.VERSION_CODES.Q)
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
    
    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted -> }
    
    fun requestNotificationPermission() {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
    
    val healthConnectPermissionLauncher = rememberLauncherForActivityResult(
        healthConnectManager.requestPermissionsActivityContract()
    ) { granted ->
        if (granted.containsAll(healthConnectManager.permissions)) {
            Log.d("HealthConnect", "Permissions granted successfully!")
            requestNotificationPermission()
        } else {
            Log.d("HealthConnect", "Permissions not granted: $granted")
            showPermissionDeniedDialog()
        }
    }
    
    LaunchedEffect(Unit) {
        val healthConnectPermission =
            healthConnectManager.hasAllPermissions(healthConnectManager.permissions)
        
        if (!healthConnectPermission) {
            Log.d("HealthConnect", "Requesting permissions...")
            healthConnectPermissionLauncher.launch(healthConnectManager.permissions)
        } else {
            requestNotificationPermission()
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