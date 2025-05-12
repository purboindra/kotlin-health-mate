package com.example.healthmate.ui.screen.my_activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.healthmate.data.HealthConnectManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class MyActivityViewModel @Inject constructor(
    private val healthConnectManager: HealthConnectManager
) : ViewModel() {
    
    private val today = LocalDate.now()
    private val formatter = DateTimeFormatter.ofPattern("d MMM", Locale("id"))
    
    private val _firstDayOfWeek = MutableStateFlow<String?>(null)
    val firstDayOfWeek = _firstDayOfWeek.asStateFlow()
    
    private val _lastDayOfWeek = MutableStateFlow<String?>(null)
    val lastDayOfWeek = _lastDayOfWeek.asStateFlow()
    
    private val firstDayOfWeekMonday =
        MutableStateFlow(today.with(DayOfWeek.MONDAY))
    private val lastDayOfWeekSunday =
        MutableStateFlow(today.with(DayOfWeek.SUNDAY))
    
    fun onWeeklyChange(direction: String) {
        if (direction == "Next") {
            val firstWeek = firstDayOfWeekMonday.value.plusWeeks(1)
            _firstDayOfWeek.value = firstWeek.format(formatter)
        } else {
            val lastWeek = lastDayOfWeekSunday.value.plusWeeks(1)
            _lastDayOfWeek.value = lastWeek.format(formatter)
        }
    }
    
}