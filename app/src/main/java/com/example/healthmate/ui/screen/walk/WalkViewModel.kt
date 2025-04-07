package com.example.healthmate.ui.screen.walk

import android.util.Log
import androidx.health.connect.client.records.WeightRecord
import androidx.lifecycle.ViewModel
import com.example.healthmate.data.HealthConnectManager
import com.example.healthmate.data.MapManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Duration
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class WalkViewModel @Inject constructor(
    private val healthConnectManager: HealthConnectManager,
    private val stepManager: MapManager
) : ViewModel() {
    
    private val _stepsCount = MutableStateFlow(0)
    val stepsCount = _stepsCount.asStateFlow()
    
    private val _distanceMeters = MutableStateFlow(0f)
    val distanceMeters = _distanceMeters.asStateFlow()
    
    private val _calories = MutableStateFlow(0f)
    val calories = _calories.asStateFlow()
    
    private val _durationMovement = MutableStateFlow<Duration?>(null)
    val durationMovement = _durationMovement.asStateFlow()
    
    private val _weightList = MutableStateFlow<List<WeightRecord>>(emptyList())
    val weightList = _weightList.asStateFlow()
    
    private var _startTime: ZonedDateTime? = null
    
    /// TODO: Use real value
    val strideLengthMeters = 0.75f
    
    init {
        _durationMovement.value = Duration.ZERO
    }
    
    fun updateStepCount(steps: Int) {
        
        if (steps == 0) return
        
        _stepsCount.value = steps
        _distanceMeters.value = steps * strideLengthMeters
        
        if (_startTime == null && steps > 0) {
            _startTime = ZonedDateTime.now()
        }
        
        _startTime?.let {
            val time = ZonedDateTime.now()
            val duration = Duration.between(it, time)
            _durationMovement.value = duration
            updateCaloriesBurned()
        }
        
        Log.d(
            "WalkViewModel",
            "updateStepCount: steps: $steps, distance: ${_distanceMeters.value}, duration: ${_durationMovement.value}, calories: ${_calories.value}, start time: $_startTime"
        )
    }
    
    fun updateCaloriesBurned() {
        val distanceInKm = _distanceMeters.value / 1000f
        val weightInKg =
            (_weightList.value.firstOrNull()?.weight?.inKilograms
                ?: 0.0).toFloat()
        val kcalPerKgPerKm = 0.9f
        
        Log.d(
            "ExerciseViewModel",
            "updateCaloriesBurned: distanceInKm: $distanceInKm, weightInKg: $weightInKg, kcalPerKgPerKm: $kcalPerKgPerKm"
        )
        
        _calories.value = distanceInKm * weightInKg * kcalPerKgPerKm
    }
}