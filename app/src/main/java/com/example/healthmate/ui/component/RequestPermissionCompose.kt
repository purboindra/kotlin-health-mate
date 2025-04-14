package com.example.healthmate.ui.component

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.healthmate.data.HealthConnectManager

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun RequestPermission(
    healthConnectManager: HealthConnectManager,
    onGranted: () -> Unit,
    onDenied: () -> Unit,
) {
    val context = LocalContext.current
    
    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACTIVITY_RECOGNITION
    )
    
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.entries.all { it.value }
        if (allGranted) {
            Log.d("Request Permission", "Permission granted successfully!")
        } else {
            Log.d("Request Permission", "Permission denied!")
            onDenied()
        }
    }
    
    LaunchedEffect(Unit) {
        
        val fineLocationGranted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        
        val activityGranted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACTIVITY_RECOGNITION
        ) == PackageManager.PERMISSION_GRANTED
        
        if (!fineLocationGranted || !activityGranted) {
            permissionLauncher.launch(
                permissions
            )
        } else {
            onGranted()
        }
    }
}