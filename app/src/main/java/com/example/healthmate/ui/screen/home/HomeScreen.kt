package com.example.healthmate.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.component.HomeScreenHeader
import com.example.healthmate.ui.component.WeeklySchedule
import com.example.healthmate.ui.component.WeeklyScheduleItem
import com.example.healthmate.util.VerticalSpacer

@Composable
fun HomeScreen(modifier: Modifier) {
    
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
    
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        item {
            HomeScreenHeader(
                modifier = Modifier
                    .height(64.dp)
                    .background(Color.Red)
            )
            24.VerticalSpacer()
            WeeklySchedule(modifier = Modifier, schedule = schedules)
        }
    }
}