package com.example.healthmate.data

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.HealthConnectClient.Companion.SDK_UNAVAILABLE
import androidx.health.connect.client.PermissionController
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.DistanceRecord
import androidx.health.connect.client.records.ExerciseSessionRecord
import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.SpeedRecord
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.records.TotalCaloriesBurnedRecord
import androidx.health.connect.client.records.WeightRecord

// The minimum android level that can use Health Connect
const val MIN_SUPPORTED_SDK = Build.VERSION_CODES.O_MR1

class HealthConnectManager(private val context: Context) {
    
    private val healthConnectClient by lazy {
        HealthConnectClient.getOrCreate(context)
    }
    
    val permissions = setOf(
        HealthPermission.getWritePermission(ExerciseSessionRecord::class),
        HealthPermission.getReadPermission(ExerciseSessionRecord::class),
        HealthPermission.getWritePermission(StepsRecord::class),
        HealthPermission.getReadPermission(StepsRecord::class),
        HealthPermission.getWritePermission(SpeedRecord::class),
        HealthPermission.getReadPermission(SpeedRecord::class),
        HealthPermission.getWritePermission(DistanceRecord::class),
        HealthPermission.getReadPermission(DistanceRecord::class),
        HealthPermission.getWritePermission(TotalCaloriesBurnedRecord::class),
        HealthPermission.getReadPermission(TotalCaloriesBurnedRecord::class),
        HealthPermission.getWritePermission(HeartRateRecord::class),
        HealthPermission.getReadPermission(HeartRateRecord::class),
        HealthPermission.getReadPermission(WeightRecord::class),
        HealthPermission.getWritePermission(WeightRecord::class),
    )
    
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    val heartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE)
    
    fun promptUpdateHealthConnect() {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data =
                Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.healthconnect")
        }
        context.startActivity(intent)
    }
    
    var availability = mutableIntStateOf(SDK_UNAVAILABLE)
        private set
    
    suspend fun findMissingPermissions(
    ) {
        val grantedPermissions = healthConnectClient.permissionController.getGrantedPermissions()
        
        val missingPermissions = permissions.filterNot { it in grantedPermissions }
        if (missingPermissions.isNotEmpty()) {
            Log.e("HealthConnect", "Missing Permissions: $missingPermissions")
        } else {
            Log.d("HealthConnect", "All permissions are granted! ✅")
        }
    }
    
    
    private fun checkAvailability() {
        availability.intValue = HealthConnectClient.getSdkStatus(context)
        Log.d(
            "HealthConnectManager",
            "checkAvailability result: ${availability.intValue}"
        )
        
        if (availability.intValue == HealthConnectClient.SDK_UNAVAILABLE_PROVIDER_UPDATE_REQUIRED) {
            Log.d(
                "HealthConnectManager", "Health connect need update"
            )
            promptUpdateHealthConnect()
        } else if (availability.intValue == SDK_UNAVAILABLE) {
            Log.d(
                "HealthConnectManager", "Health connect not available"
            )
        } else {
            Log.d(
                "HealthConnectManager", "Health connect available"
            )
        }
    }
    
    init {
        checkAvailability()
    }
    
    suspend fun hasAllPermissions(permission: Set<String>): Boolean {
        return healthConnectClient.permissionController.getGrantedPermissions()
            .containsAll(permission)
    }
    
    suspend fun requestMissingPermissions(permissions: Set<String>) {
        val grantedPermissions = healthConnectClient.permissionController.getGrantedPermissions()
        val missingPermissions = permissions.filterNot { it in grantedPermissions }
        
        if (missingPermissions.isNotEmpty()) {
            val requestPermissionActivityContract =
                PermissionController.createRequestPermissionResultContract()
            
            val launcher = (context as ComponentActivity).registerForActivityResult(requestPermissionActivityContract) { granted ->
                if (granted.containsAll(missingPermissions)) {
                    Log.d("HealthConnect", "Missing permissions granted: $granted")
                } else {
                    Log.d("HealthConnect", "Some permissions were denied: $granted")
                }
            }
            launcher.launch(missingPermissions.toSet())
        } else {
            Log.d("HealthConnect", "All necessary permissions are already granted.")
        }
    }
    
    
    suspend fun logGrantedPermissions() {
        val grantedPermissions = healthConnectClient.permissionController.getGrantedPermissions()
        Log.d("HealthConnect", "Granted Permissions: $grantedPermissions")
    }
    
    fun requestPermissionActivityContract(): ActivityResultContract<Set<String>, Set<String>> {
        return PermissionController.createRequestPermissionResultContract()
    }
    
    fun requestPermissions(permission: Set<String>) {
        val requestPermissionActivityContract =
            PermissionController.createRequestPermissionResultContract()
        val requestPermissions =
            (context as ComponentActivity).registerForActivityResult(
                requestPermissionActivityContract
            ) { granted ->
                if (granted.containsAll(permission)) {
                    Log.d(
                        "HealthConnectManager",
                        "Permissions granted: $granted"
                    )
                } else {
                    Log.d(
                        "HealthConnectManager",
                        "Permissions not granted: $granted"
                    )
                }
            }
        requestPermissions.launch(permission)
    }
    
    suspend fun revokeAllPermissions() {
        healthConnectClient.permissionController.revokeAllPermissions()
    }
    
}