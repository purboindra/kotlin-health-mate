package com.example.healthmate.ui.screen.home

import android.Manifest
import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.StepsRecord
import com.example.healthmate.data.HealthConnectManager
import com.example.healthmate.ui.component.HomeScreenHeader
import com.example.healthmate.ui.component.TodayMeal
import com.example.healthmate.ui.component.WeeklyHoursSchedule
import com.example.healthmate.ui.component.WeeklyHoursScheduleItem
import com.example.healthmate.ui.component.WeeklySchedule
import com.example.healthmate.ui.component.WeeklyScheduleItem
import com.example.healthmate.util.VerticalSpacer

const val channelId = "health_sync_channel"

@Composable
fun HomeScreen(modifier: Modifier) {
    
    val context = LocalContext.current
    
    fun createNotificationChannel() {
        val channel = NotificationChannel(
            channelId,
            "Health Sync",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Used for syncing health data"
            setShowBadge(true)
        }
        
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
    
    fun scheduleWeeklySync() {
        createNotificationChannel()
        
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Syncing Health Data").setContentText(
                "Processing step count..."
            ).setSmallIcon(R.drawable.ic_notification_clear_all)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setOngoing(true).build()
        
        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager
        
        notificationManager.notify(1001, notification)

//        val syncRequest = PeriodicWorkRequestBuilder<HealthSyncWorker>(
//            7, TimeUnit.DAYS,
//            1, TimeUnit.HOURS
//        ).setConstraints(
//            Constraints.Builder().setRequiresBatteryNotLow(true).build()
//        ).build()
//
//        WorkManager.getInstance(context).enqueue(syncRequest)
    }
    
    val schedules = listOf(
        WeeklyScheduleItem(
            label = "Sun",
            hasComplete = false
        ), WeeklyScheduleItem(
            label = "Mon",
            hasComplete = true
        ), WeeklyScheduleItem(
            label = "Tue",
            hasComplete = true
        ), WeeklyScheduleItem(
            label = "Wen",
            hasComplete = false
        ), WeeklyScheduleItem(
            label = "Thu",
            hasComplete = true
        ), WeeklyScheduleItem(
            label = "Fri",
            hasComplete = false
        ),
        WeeklyScheduleItem(
            label = "Thu",
            hasComplete = false
        )
    )
    
    
    val hoursSchedule = listOf(
        WeeklyHoursScheduleItem(
            label = "Sun",
            hasComplete = false,
            duration = 3,
        ), WeeklyHoursScheduleItem(
            label = "Mon",
            hasComplete = true,
            duration = 5,
        ), WeeklyHoursScheduleItem(
            label = "Tue",
            hasComplete = true,
            duration = 2,
        ), WeeklyHoursScheduleItem(
            label = "Wen",
            hasComplete = false,
            duration = 0,
        ), WeeklyHoursScheduleItem(
            label = "Thu",
            hasComplete = true,
            duration = 3,
        ), WeeklyHoursScheduleItem(
            label = "Fri",
            hasComplete = false,
            duration = 0
        ),
        WeeklyHoursScheduleItem(
            label = "Thu",
            hasComplete = false,
            duration = 3,
        )
    )
    
    val healthConnectManager = remember {
        HealthConnectManager(context)
    }
    
    val permisisons = setOf(
        Manifest.permission.ACTIVITY_RECOGNITION,
        HealthPermission.getWritePermission(StepsRecord::class),
        HealthPermission.getReadPermission(StepsRecord::class),
    )
    
    
    val permissionLauncher = rememberLauncherForActivityResult(
        healthConnectManager.requestPermissionsActivityContract()
    ) { granted ->
        if (granted.containsAll(
                permisisons
            )
        ) {
            Log.d("HealthConnect", "Permissions granted successfully!")
        }
    }
    
    LaunchedEffect(Unit) {
        
        val allPermissionGranted = permisisons.all {
            ContextCompat.checkSelfPermission(
                context,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
        
        if (allPermissionGranted
        ) {
            healthConnectManager.readStepsRecord()
        } else {
            permissionLauncher.launch(healthConnectManager.permissions)
        }
    }
    
    LazyColumn(
    
    ) {
        item {
            HomeScreenHeader(
                modifier = Modifier
                    .height(64.dp)
                    .background(Color.Red)
            )
            24.VerticalSpacer()
            WeeklySchedule(modifier = Modifier, schedule = schedules)
            18.VerticalSpacer()
            WeeklyHoursSchedule(
                modifier = modifier,
                schedule = hoursSchedule
            )
            18.VerticalSpacer()
            TodayMeal()
            ElevatedButton(
                onClick = {
                    scheduleWeeklySync()
                }
            ) {
                Text("Test Work Manager")
            }
        }
    }
}