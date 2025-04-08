package com.example.healthmate.data

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.runtime.mutableIntStateOf
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.HealthConnectClient.Companion.SDK_UNAVAILABLE
import androidx.health.connect.client.HealthConnectFeatures
import androidx.health.connect.client.PermissionController
import androidx.health.connect.client.changes.Change
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.DistanceRecord
import androidx.health.connect.client.records.ExerciseSessionRecord
import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.SpeedRecord
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.records.TotalCaloriesBurnedRecord
import androidx.health.connect.client.records.WeightRecord
import androidx.health.connect.client.records.metadata.Metadata
import androidx.health.connect.client.request.ChangesTokenRequest
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import androidx.health.connect.client.units.Energy
import androidx.health.connect.client.units.Mass
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.time.Instant
import java.time.ZonedDateTime
import java.time.temporal.ChronoField
import kotlin.random.Random

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
    
    fun promptUpdateHealthConnect() {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data =
                Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.healthconnect")
        }
        context.startActivity(intent)
    }
    
    var availability = mutableIntStateOf(SDK_UNAVAILABLE)
    
    private fun checkAvailability() {
        availability.intValue = HealthConnectClient.getSdkStatus(context)
        Log.d(
            "HealthConnectManager",
            "checkAvailability result: ${availability.intValue}"
        )
        
        when (availability.intValue) {
            HealthConnectClient.SDK_UNAVAILABLE_PROVIDER_UPDATE_REQUIRED -> {
                Log.d(
                    "HealthConnectManager", "Health connect need update"
                )
                promptUpdateHealthConnect()
            }
            
            SDK_UNAVAILABLE -> {
                // TODO DO SOMETHING
                Log.d(
                    "HealthConnectManager", "Health connect not available"
                )
            }
            
            else -> {
                Log.d(
                    "HealthConnectManager", "Health connect available"
                )
            }
        }
    }
    
    init {
        checkAvailability()
    }
    
    suspend fun hasAllPermissions(permission: Set<String>): Boolean {
        return healthConnectClient.permissionController.getGrantedPermissions()
            .containsAll(permission)
    }
    
    fun requestPermissionsActivityContract(): ActivityResultContract<Set<String>, Set<String>> {
        return PermissionController.createRequestPermissionResultContract()
    }
    
    suspend fun revokeAllPermissions() {
        healthConnectClient.permissionController.revokeAllPermissions()
    }
    
    suspend fun readWeightInputs(
        start: Instant, end: Instant
    ): List<WeightRecord> {
        
        Log.d(
            "HealthConnectManager", "readWeightInputs start: $start, end: $end"
        )
        
        val request = ReadRecordsRequest(
            WeightRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        
        Log.d(
            "HealthConnectManager",
            "readWeightInputs response: ${response.records}"
        )
        
        return response.records
    }
    
    suspend fun writeWeightInput(weightInput: Double) {
        val time = ZonedDateTime.now().withNano(0)
        val weightRecord = WeightRecord(
            time = time.toInstant(),
            metadata = Metadata.manualEntry(),
            weight = Mass.kilograms(weightInput),
            zoneOffset = time.offset
        )
        val records = listOf(weightRecord)
        try {
            healthConnectClient.insertRecords(records)
            Toast.makeText(
                context, "Successfully insert records!", Toast.LENGTH_LONG
            ).show()
        } catch (e: Throwable) {
            Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT)
                .show()
        }
    }
    
    suspend fun readExerciseSessions(
        start: Instant,
        end: Instant
    ): List<ExerciseSessionRecord> {
        val request = ReadRecordsRequest(
            recordType = ExerciseSessionRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        return response.records
    }
    
    suspend fun readStepsRecord(
    ): List<StepsRecord> {
        
        val startOfWeek = ZonedDateTime.now().with(ChronoField.DAY_OF_WEEK, 1)
        val endOfWeek = ZonedDateTime.now()
        
        Log.d(
            "HealthConnectManager",
            "readStepsRecord startOfWeek: $startOfWeek, endOfWeek: $endOfWeek"
        )
        
        val request = ReadRecordsRequest(
            recordType = StepsRecord::class,
            timeRangeFilter = TimeRangeFilter.between(
                startOfWeek.toInstant(),
                endOfWeek.toInstant()
            )
        )
        val response = healthConnectClient.readRecords(request)
        
        
        Log.d(
            "HealthConnectManager",
            "readStepsRecord response: ${response.records}"
        )
        
        return response.records
    }
    
    
    @SuppressLint("RestrictedApi")
    suspend fun writeExerciseSession(
        start: ZonedDateTime,
        end: ZonedDateTime,
        steps: Int,
        calories: Double
    ) {
        healthConnectClient.insertRecords(
            listOf(
                ExerciseSessionRecord(
                    metadata = Metadata.manualEntry(),
                    startTime = start.toInstant(),
                    startZoneOffset = start.offset,
                    endTime = end.toInstant(),
                    endZoneOffset = end.offset,
                    exerciseType = ExerciseSessionRecord.EXERCISE_TYPE_RUNNING,
                    title = "My Run #${Random.nextInt(0, 60)}"
                ), StepsRecord(
                    metadata = Metadata.manualEntry(),
                    startTime = start.toInstant(),
                    startZoneOffset = start.offset,
                    endTime = end.toInstant(),
                    endZoneOffset = end.offset,
                    count = steps.toLong()
                ), TotalCaloriesBurnedRecord(
                    metadata = Metadata.manualEntry(),
                    startTime = start.toInstant(),
                    startZoneOffset = start.offset,
                    endTime = end.toInstant(),
                    endZoneOffset = end.offset,
                    energy = Energy.calories(calories)
                )
            )
        )
    }
    
    private fun buildHeartRateSeries(
        sessionStartTime: ZonedDateTime,
        sessionEndTime: ZonedDateTime,
    ): HeartRateRecord {
        val samples = mutableListOf<HeartRateRecord.Sample>()
        var time = sessionStartTime
        while (time.isBefore(sessionEndTime)) {
            samples.add(
                HeartRateRecord.Sample(
                    time = time.toInstant(),
                    beatsPerMinute = (80 + Random.nextInt(80)).toLong()
                )
            )
            time = time.plusSeconds(30)
        }
        return HeartRateRecord(
            metadata = Metadata.manualEntry(),
            startTime = sessionStartTime.toInstant(),
            startZoneOffset = sessionStartTime.offset,
            endTime = sessionEndTime.toInstant(),
            endZoneOffset = sessionEndTime.offset,
            samples = samples
        )
    }
    
    fun isFeatureAvailable(feature: Int): Boolean {
        return healthConnectClient.features.getFeatureStatus(feature) == HealthConnectFeatures.FEATURE_STATUS_AVAILABLE
    }
    
    suspend fun getChangesToken(): String {
        return healthConnectClient.getChangesToken(
            ChangesTokenRequest(
                setOf(
                    WeightRecord::class
                )
            )
        )
    }
    
    fun getChanges(token: String): Flow<ChangesMessage> = flow {
        var nextChangesToken = token
        do {
            val response = healthConnectClient.getChanges(nextChangesToken)
            if (response.changesTokenExpired) {
                throw IOException("Changes token has expired")
            }
            emit(ChangesMessage.ChangeLists(response.changes))
            nextChangesToken = response.nextChangesToken
        } while (response.hasMore)
        emit(ChangesMessage.NoMoreChanges(nextChangesToken))
    }
}

sealed class ChangesMessage {
    data class NoMoreChanges(val nextChangesToken: String) : ChangesMessage()
    data class ChangeLists(val changes: List<Change>) : ChangesMessage()
}