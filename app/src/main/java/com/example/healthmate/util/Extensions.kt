package com.example.healthmate.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Composable
fun Int.VerticalSpacer() {
    Box(modifier = Modifier.height(this.dp))
}

@Composable
fun Int.HorizontalSpacer() {
    Box(modifier = Modifier.width(this.dp))
}

fun Dp.dpToFloat(): Float {
    return this.value
}

fun Long.millisToLocaleDate(): LocalDate {
    return Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault())
        .toLocalDate()
}