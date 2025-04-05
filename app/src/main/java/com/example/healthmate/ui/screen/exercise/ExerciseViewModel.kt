package com.example.healthmate.ui.screen.exercise

import android.util.Log
import androidx.health.connect.client.HealthConnectFeatures
import androidx.health.connect.client.records.WeightRecord
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthmate.data.ChangesMessage
import com.example.healthmate.data.HealthConnectManager
import com.example.healthmate.data.SensorManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

const val TAG = "ExerciseViewModel"

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val healthConnectManager: HealthConnectManager,
    private val sensorManager: SensorManager
) : ViewModel() {
    
    private val _weightQuery = MutableStateFlow("")
    val weightQuery = _weightQuery.asStateFlow()
    
    private val _weightList = MutableStateFlow<List<WeightRecord>>(emptyList())
    val weightList = _weightList.asStateFlow()
    
    private val _backgroundReadAvailable = MutableStateFlow(false)
    val backgroundReadAvailable = _backgroundReadAvailable.asStateFlow()
    
    private val _historyReadAvailable = MutableStateFlow(false)
    val historyReadAvailable = _historyReadAvailable.asStateFlow()
    
    private val _changeMessageToken = MutableStateFlow<ChangesMessage?>(null)
    val changeMessageToken = _changeMessageToken.asStateFlow()
    
    fun onWeightChange(weight: String) {
        _weightQuery.value = weight
    }
    
    init {
        _backgroundReadAvailable.value =
            healthConnectManager.isFeatureAvailable(
                HealthConnectFeatures.FEATURE_READ_HEALTH_DATA_IN_BACKGROUND
            )
        
        _historyReadAvailable.value = healthConnectManager.isFeatureAvailable(
            HealthConnectFeatures.FEATURE_READ_HEALTH_DATA_HISTORY
        )
        
        val hasSensor = sensorManager.hasStepSensor()
        
        Log.d(TAG, "hasSensor: $hasSensor")
        
        if (hasSensor) {
            sensorManager.registerListener()
        }
        
        Log.d(
            "ExerciseViewModel",
            "hasStepSensor: $hasSensor"
        )
        
        Log.d(
            "ExerciseViewModel",
            "backgroundReadAvailable: ${backgroundReadAvailable.value}"
        )
    }
    
    
    fun formatInstantToDate(
        instant: Instant,
        pattern: String = "yyyy-MM-dd HH:mm:ss",
        timeZone: String = "UTC"
    ): String {
        val zonedDateTime =
            ZonedDateTime.ofInstant(instant, ZoneId.of(timeZone))
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return formatter.format(zonedDateTime)
        
    }
    
    fun writeWeightInput() {
        viewModelScope.launch {
            healthConnectManager.writeWeightInput(weightQuery.value.toDouble())
        }
    }
    
    fun getWeight() {
        viewModelScope.launch {
            val start = ZonedDateTime.now().minusMonths(1).toInstant()
            val end = ZonedDateTime.now().toInstant()
            val weights = healthConnectManager.readWeightInputs(start, end)
            _weightList.value = weights
        }
    }
    
    fun getChanges() {
        viewModelScope.launch {
            val newTokens = healthConnectManager.getChangesToken()
            Log.d("HealthConnect", "newTokens: $newTokens")
            val result = healthConnectManager.getChanges(newTokens)
            result.collectLatest { state ->
                when (state) {
                    is ChangesMessage.NoMoreChanges -> {
                        Log.d(
                            "HealthConnect",
                            "no more changes: ${state.nextChangesToken}"
                        )
//                        _changeMessageToken.value =
//                            ChangesMessage.NoMoreChanges(state.nextChangesToken)
                    }
                    
                    is ChangesMessage.ChangeLists -> {
                        val token =
                            state.changes
                        Log.d("HealthConnect", "changes: $token")
                    }
                }
            }
        }
    }
    
    fun unregisterStepListener() {
        sensorManager.unregisterListener()
    }
    
}