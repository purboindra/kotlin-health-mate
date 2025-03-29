package com.example.healthmate

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.health.connect.client.HealthConnectClient
import com.example.healthmate.data.HealthConnectManager
import dagger.hilt.android.AndroidEntryPoint

private val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val context = this
        
        val healthConnectManager =
            HealthConnectManager(context)
        
        val status = HealthConnectClient.getSdkStatus(context)
        
        if (status != HealthConnectClient.SDK_AVAILABLE) {
            Toast.makeText(
                context,
                "Health Connect not available!",
                Toast.LENGTH_LONG
            ).show()
            return
        }
        
        setContent {
            HealthMateApp(healthConnectManager)
        }
    }
}