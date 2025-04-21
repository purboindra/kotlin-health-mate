package com.example.healthmate

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.health.connect.client.HealthConnectClient
import androidx.navigation.compose.rememberNavController
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.healthmate.data.HealthConnectManager
import com.example.healthmate.data.HealthSyncWorker
import com.example.healthmate.data.SensorManager
import com.example.healthmate.ui.screen.HealthConnectUnavailable
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

private val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    
    var context: Context? = null
    
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        context = this
        
        val healthConnectManager =
            HealthConnectManager(context!!)
        
        val status = HealthConnectClient.getSdkStatus(context!!)
        val healthConnectUnavailable =
            status != HealthConnectClient.SDK_AVAILABLE
        
        if (status != HealthConnectClient.SDK_AVAILABLE) {
            Toast.makeText(
                context,
                "Health Connect not available!",
                Toast.LENGTH_LONG
            ).show()
        }
        
        setContent {
            
            val navHostController = rememberNavController()
            
            if (healthConnectUnavailable) {
                HealthConnectUnavailable(navHostController = navHostController)
            } else {
                HealthMateApp(healthConnectManager)
            }
        }
    }
    
    override fun onResume() {
        super.onResume()
        SensorManager(context!!, {
            Log.d(TAG, "onResume called: $it")
        }).registerListener()
    }
    
    override fun onPause() {
        super.onPause()
        SensorManager(context!!, {
            Log.d(TAG, "onPause called: $it")
        }).unregisterListener()
    }
    
    private fun scheduleWeeklySync() {
        val syncRequest = PeriodicWorkRequestBuilder<HealthSyncWorker>(
            7, TimeUnit.DAYS,
            1, TimeUnit.HOURS
        ).setConstraints(
            Constraints.Builder().setRequiresBatteryNotLow(true).build()
        ).build()
        
        WorkManager.getInstance(this).enqueue(syncRequest)
        
    }
}