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
import com.example.healthmate.data.HealthConnectManager
import com.example.healthmate.data.SensorManager
import com.example.healthmate.ui.component.HealthConnectUnavailable
import dagger.hilt.android.AndroidEntryPoint

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
        
        val isGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACTIVITY_RECOGNITION
        ) == PackageManager.PERMISSION_GRANTED
        
        if (!isGranted) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
                100 // request code
            )
            
        }
        
        val isGrantedLocation = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        
        if (!isGrantedLocation) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                100 // request code
            )
            
        }
        
        setContent {
            if (healthConnectUnavailable) {
                HealthConnectUnavailable()
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
}