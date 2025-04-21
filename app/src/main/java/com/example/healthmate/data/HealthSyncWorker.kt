package com.example.healthmate.data

import android.content.Context
import android.util.Log
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import java.time.ZonedDateTime
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit

class HealthSyncWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    private val healthConnectClient by lazy {
        HealthConnectClient.getOrCreate(appContext)
    }
    
    override suspend fun doWork(): Result {
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
}
