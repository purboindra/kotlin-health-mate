package com.example.healthmate.ui.screen.my_activity

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.healthmate.data.HealthConnectManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MyActivityViewModel @Inject constructor(
    private val healthConnectManager: HealthConnectManager
) : ViewModel() {
    
    private val today = LocalDate.now()
    
    private val firstDayOfWeekMonday =
        MutableStateFlow(today.with(DayOfWeek.MONDAY))
    private val lastDayOfWeekSunday =
        MutableStateFlow(today.with(DayOfWeek.SUNDAY))
    
    private val _firstDayOfWeek =
        MutableStateFlow<LocalDate?>(firstDayOfWeekMonday.value)
    val firstDayOfWeek = _firstDayOfWeek.asStateFlow()
    
    private val _lastDayOfWeek =
        MutableStateFlow<LocalDate?>(lastDayOfWeekSunday.value)
    val lastDayOfWeek = _lastDayOfWeek.asStateFlow()
    
    fun onWeeklyChange(direction: String) {
        if (direction == "Next") {
            _firstDayOfWeek.value = _firstDayOfWeek.value?.plusWeeks(1)
          _lastDayOfWeek.value = _lastDayOfWeek.value?.plusWeeks(1)
        } else {
            _firstDayOfWeek.value = _firstDayOfWeek.value?.minusWeeks(1)
            _lastDayOfWeek.value = _lastDayOfWeek.value?.minusWeeks(1)
        }
    }
}