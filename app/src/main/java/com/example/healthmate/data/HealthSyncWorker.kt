package com.example.healthmate.data

import android.R
import android.app.Notification
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import java.time.ZonedDateTime
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit

class HealthSyncWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    
    private val healthConnectClient by lazy {
        HealthConnectClient.getOrCreate(appContext)
    }
    
    override suspend fun getForegroundInfo(): ForegroundInfo {
        return ForegroundInfo(
            NOTIFICATION_ID, createSynNotification(applicationContext)
        )
    }
    
    override suspend fun doWork(): Result {
        
        setForeground(getForegroundInfo())
        
        try {
            
            val steps = fetchWeeklySteps()
            
            Log.d("HealthSyncWorker", "doWork: steps: $steps")
            
            return Result.success()
            
        } catch (e: Throwable) {
            return Result.failure()
        }
    }
    
    
    private suspend fun fetchWeeklySteps(): List<StepsRecord> {
        val startOfWeek = ZonedDateTime.now()
            .with(ChronoField.DAY_OF_WEEK, 1)
            .truncatedTo(ChronoUnit.DAYS)
        val endOfWeek = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS)
        
        val request = ReadRecordsRequest(
            recordType = StepsRecord::class,
            timeRangeFilter = TimeRangeFilter.between(
                startTime = startOfWeek.toInstant(),
                endTime = endOfWeek.toInstant()
            )
        )
        return healthConnectClient.readRecords(request).records
    }
    
    private fun createSynNotification(context: Context): Notification {
        val channelId = "health_sync_channel"
        
        return NotificationCompat.Builder(context, channelId)
            .setContentTitle("Syncing Health Data").setContentText(
                "Processing step count..."
            ).setSmallIcon(R.drawable.ic_notification_clear_all).setPriority(
                NotificationCompat.PRIORITY_LOW
            ).setOngoing(true).build()
    }
    
    companion object {
        const val NOTIFICATION_ID = 1001
    }
    
}
