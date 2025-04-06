package com.example.healthmate.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.healthmate.ui.screen.exercise.ExerciseViewModel
import com.example.healthmate.util.HorizontalSpacer

@SuppressLint("DefaultLocale")
@Composable
fun CardioInformation(
    exerciseViewModel: ExerciseViewModel
) {
    
    val distanceMeters by exerciseViewModel.distanceMeters.collectAsStateWithLifecycle()
    val durationMovement by exerciseViewModel.durationMovement.collectAsStateWithLifecycle()
    val calories by exerciseViewModel.calories.collectAsStateWithLifecycle()
    
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        CardioInformationItem(
            value = String.format("%.1f", calories).toString(),
            label = "Kal"
        )
        10.HorizontalSpacer()
        CardioInformationItem(
            value = String.format("%.1f", (distanceMeters / 1000)).toString(),
            label = "km"
        )
        10.HorizontalSpacer()
        CardioInformationItem(
            value = durationMovement.toInt().toString(),
            label = "Menit Bergerak"
        )
    }
}