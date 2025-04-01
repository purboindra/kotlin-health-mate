package com.example.healthmate.ui.screen.exercise

import androidx.health.connect.client.records.WeightRecord
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthmate.data.HealthConnectManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val healthConnectManager: HealthConnectManager
) : ViewModel() {
    
    private val _weightQuery = MutableStateFlow("")
    val weightQuery = _weightQuery.asStateFlow()
    
    private val _weightList = MutableStateFlow<List<WeightRecord>>(emptyList())
    val weightList = _weightList.asStateFlow()
    
    fun onWeightChange(weight: String) {
        _weightQuery.value = weight
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
    
}