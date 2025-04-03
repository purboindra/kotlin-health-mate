package com.example.healthmate.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.healthmate.util.HorizontalSpacer

@Composable
fun CardioInformation() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        CardioInformationItem(
            value = "0",
            label = "Kal"
        )
        10.HorizontalSpacer()
        CardioInformationItem(
            value = "0",
            label = "km"
        )
        10.HorizontalSpacer()
        CardioInformationItem(
            value = "0",
            label = "Menit Bergerak"
        )
    }
}