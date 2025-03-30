package com.example.healthmate.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Int.VerticalSpacer() {
    Box(modifier = Modifier.height(this.dp))
}

@Composable
fun Int.HorizontalSpacer() {
    Box(modifier = Modifier.width(this.dp))
}