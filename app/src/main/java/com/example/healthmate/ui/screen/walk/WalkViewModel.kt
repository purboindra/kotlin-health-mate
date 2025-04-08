package com.example.healthmate.ui.screen.walk

import android.util.Log
import androidx.health.connect.client.records.WeightRecord
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthmate.data.HealthConnectManager
import com.example.healthmate.data.MapManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint
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
    
    /// MAPS
    private val _locations = MutableStateFlow<List<GeoPoint>>(emptyList())
    val locations = _locations.asStateFlow()
    
    private var _startTime: ZonedDateTime? = null
    
    private var _timerJob: Job? = null
    
    /// TODO: Use real value
    val strideLengthMeters = 0.75f
    
    init {
        _durationMovement.value = Duration.ZERO
    }
    
    fun addLocation(point: GeoPoint) {
        _locations.value = _locations.value + point
    }
    
    fun startTimer() {
        if (_startTime == null) {
            _startTime = ZonedDateTime.now()
        }
        
        if (_timerJob?.isActive == true) return
        
        _timerJob = viewModelScope.launch {
            while (isActive) {
                val now = ZonedDateTime.now()
                val duration = Duration.between(_startTime, now)
                _durationMovement.value = duration
                delay(1000L)
            }
        }
    }
    
    fun stopTimer() {
        _timerJob?.cancel()
        _timerJob = null
    }
    
    fun updateStepCount(steps: Int) {
        
        Log.d("WalkViewModel", "updateStepCount: steps: $steps")
        
        Log.d(
            "WalkViewModel",
            "_durationMovement.value: ${_durationMovement.value}"
        )
        
        
        if (steps == 0) return
        
        updateCaloriesBurned()
        
        _stepsCount.value = steps
        _distanceMeters.value = steps * strideLengthMeters
        
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